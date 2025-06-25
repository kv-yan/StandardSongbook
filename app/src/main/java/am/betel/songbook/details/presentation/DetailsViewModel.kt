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
    private val songIndex: String,
    getSongByIndexUseCase: GetSongByIndexUseCase,
) : ViewModel() {
    private val _currentSong = MutableStateFlow<Song?>(Song())
    val currentSongs = _currentSong.asStateFlow()

    init {
        getSongByIndexUseCase(songIndex).onEach {
            _currentSong.value = it
        }.launchIn(viewModelScope)
    }

}