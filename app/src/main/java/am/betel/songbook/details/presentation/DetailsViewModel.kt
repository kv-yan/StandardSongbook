package am.betel.songbook.details.presentation

import am.betel.songbook.bookmark.domain.usecase.AddToFavoritesUseCase
import am.betel.songbook.bookmark.domain.usecase.IsFavoriteUseCase
import am.betel.songbook.bookmark.domain.usecase.RemoveFromFavoritesUseCase
import am.betel.songbook.common.presentation.ui.state.UiEvent
import am.betel.songbook.details.domain.usecase.GetSongByIndexUseCase
import am.betel.songs.domain.model.Song
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(
    songIndex: Int,
    private val getSongByIndexUseCase: GetSongByIndexUseCase,
    private val addToFavoritesUseCaseImpl: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
) : ViewModel() {

    companion object {
        private const val MIN_INDEX = 1
        private const val MAX_INDEX = 1000
    }

    private var favoriteJob: Job? = null

    private val _currentSong = MutableStateFlow<Song?>(Song())
    val currentSongs = _currentSong.asStateFlow()

    private val _currentIndex = MutableStateFlow(songIndex)

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent?>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getSongByIndexUseCase(songIndex).onEach {
            _currentSong.value = it
            it?.let { song -> observeFavoriteState(song) }
        }.launchIn(viewModelScope)
    }

    private fun getSongByIndex(index: Int) {
        getSongByIndexUseCase(index).onEach {
            _currentSong.value = it
            it?.let { song -> observeFavoriteState(song) }
        }.launchIn(viewModelScope)
    }

    fun loadNextSong() {
        if (_currentIndex.value < MAX_INDEX) {
            _currentIndex.value++
            getSongByIndex(_currentIndex.value)
        }
    }

    fun loadPrevSong() {
        if (_currentIndex.value > MIN_INDEX) {
            _currentIndex.value--
            getSongByIndex(_currentIndex.value)
        }
    }

    fun toggleFavorite() {
        val song = _currentSong.value ?: return

        viewModelScope.launch {
            if (_isFavorite.value) {
                // update button state first
                _isFavorite.value = false
                removeFromFavoritesUseCase(song)
                _eventFlow.emit(UiEvent.ShowMessage("Էջանշումը հանվել է"))
            } else {
                _isFavorite.value = true
                addToFavoritesUseCaseImpl(song)
                _eventFlow.emit(UiEvent.ShowMessage("Էջանշումը կատարվել է"))
            }
        }
    }

    private fun observeFavoriteState(song: Song) {
        favoriteJob?.cancel()
        favoriteJob = isFavoriteUseCase(song).onEach {
            _isFavorite.value = it
        }.launchIn(viewModelScope)
    }
}
