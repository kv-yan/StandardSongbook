package am.betel.songbook.search.domain.usecase

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SearchSongByTextUseCase {
    operator fun invoke(query: String): Flow<List<Song>>
}