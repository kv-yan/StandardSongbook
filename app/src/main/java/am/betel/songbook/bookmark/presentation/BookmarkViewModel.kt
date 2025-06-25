package am.betel.songbook.bookmark.presentation

import am.betel.songbook.bookmark.domain.usecase.GetFavoriteSongsUseCase
import am.betel.songbook.bookmark.domain.usecase.RemoveFromFavoritesUseCase
import am.betel.songs.domain.model.Song
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BookmarkViewModel(
    getFavoriteSongsUseCase: GetFavoriteSongsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : ViewModel() {

    private val _favoriteSongs = MutableStateFlow<List<Song>>(emptyList())
    val favoriteSongs: StateFlow<List<Song>> = _favoriteSongs.asStateFlow()

    init {
        getFavoriteSongsUseCase().onEach {
            _favoriteSongs.value = it
        }.launchIn(viewModelScope)
    }

    fun removeFavoriteSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase(song)
        }
    }
}