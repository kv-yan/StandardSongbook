package am.betel.songbook.bookmark.data.usecase

import am.betel.songbook.bookmark.domain.usecase.GetFavoriteSongsUseCase
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteSongsUseCaseImpl(
    private val songRepository: SongRepository
) : GetFavoriteSongsUseCase {
    override fun invoke(): Flow<List<Song>> = songRepository.getFavoriteSongs()

}