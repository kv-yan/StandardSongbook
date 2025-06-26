package am.betel.songbook.search.data.usecase

import am.betel.songbook.search.domain.usecase.SearchSongByTextUseCase
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class SearchSongByTextUseCaseImpl(
    private val songRepository: SongRepository,
) : SearchSongByTextUseCase {
    override fun invoke(query: String): Flow<List<Song>> = songRepository.searchSongsByText(query)
}