package am.betel.songbook.bookmark.domain.usecase

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface GetFavoriteSongsUseCase {
    operator fun invoke(): Flow<List<Song>>
}