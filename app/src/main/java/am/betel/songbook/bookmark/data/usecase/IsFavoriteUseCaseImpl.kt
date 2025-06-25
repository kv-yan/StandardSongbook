package am.betel.songbook.bookmark.data.usecase

import am.betel.songbook.bookmark.domain.usecase.IsFavoriteUseCase
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteUseCaseImpl(
    private val songRepository: SongRepository
) : IsFavoriteUseCase {
    override fun invoke(song: Song): Flow<Boolean> = songRepository.isFavorite(song)
}