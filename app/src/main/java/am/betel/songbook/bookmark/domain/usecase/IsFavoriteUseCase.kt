package am.betel.songbook.bookmark.domain.usecase

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface IsFavoriteUseCase {
    operator fun invoke(song: Song): Flow<Boolean>

}