package am.betel.songbook.details.presentation

import am.betel.songbook.details.domain.usecase.GetSongByIndexUseCase
import am.betel.songs.domain.model.Song
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailsViewModel(
    songIndex: String,
    private val getSongByIndexUseCase: GetSongByIndexUseCase,
) : ViewModel() {

    companion object {
        private const val MIN_INDEX = 1
        private const val MAX_INDEX = 1000
    }

    private val _currentSong = MutableStateFlow<Song?>(Song())
    val currentSongs = _currentSong.asStateFlow()

    private val _currentIndex = MutableStateFlow(songIndex.toInt())

    init {
        getSongByIndexUseCase(songIndex).onEach {
            _currentSong.value = it
        }.launchIn(viewModelScope)
    }

    private fun getSongByIndex(index: String) {
        getSongByIndexUseCase(index).onEach {
            _currentSong.value = it
        }.launchIn(viewModelScope)
    }

    fun loadNextSong() {
        if (_currentIndex.value < MAX_INDEX) {
            _currentIndex.value++
            getSongByIndex(_currentIndex.value.toString())
        }
    }

    fun loadPrevSong() {
        if (_currentIndex.value > MIN_INDEX) {
            _currentIndex.value--
            getSongByIndex(_currentIndex.value.toString())
        }
    }
}
