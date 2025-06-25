package am.betel.songbook.bookmark.presentation

import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BookmarkViewModel(
    private val songRepository: SongRepository,
) : ViewModel() {

    private val _favoriteSongs = MutableStateFlow<List<Song>>(emptyList())
    val favoriteSongs: StateFlow<List<Song>> = _favoriteSongs.asStateFlow()

    init {
        songRepository.getFavoriteSongs().onEach {
            _favoriteSongs.value = it

        }.launchIn(viewModelScope)
    }
}