package am.betel.songbook.search.presentation

import am.betel.songbook.search.domain.usecase.SearchSongByTextUseCase
import am.betel.songs.domain.model.Song
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    val searchSongByTextUseCase: SearchSongByTextUseCase,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _foundedSongs = MutableStateFlow<List<Song>>(emptyList())
    val foundedSongs = _foundedSongs.asStateFlow()

    private val _nothingFounded = MutableStateFlow(false)
    val nothingFounded = _nothingFounded.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun onSearchClick() {
        _isLoading.value = true
        _nothingFounded.value = false
        searchSongByTextUseCase(_searchQuery.value).onEach {
            _foundedSongs.value = it
            _nothingFounded.value = it.isEmpty()
            _isLoading.value = false
        }.catch {
            _foundedSongs.value = emptyList()
            _nothingFounded.value = true
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }
}