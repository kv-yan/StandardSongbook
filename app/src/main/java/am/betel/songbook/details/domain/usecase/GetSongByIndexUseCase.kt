package am.betel.songbook.details.domain.usecase

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface GetSongByIndexUseCase {
    operator fun invoke(songIndex: String): Flow<Song?>
}