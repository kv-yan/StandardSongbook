package am.betel.songbook.details.data.usecase

import am.betel.songs.domain.model.Song
import am.betel.songbook.details.domain.usecase.GetSongByIndexUseCase
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class GetSongByIndexUseCaseImpl(
    private val songRepository: SongRepository,
) : GetSongByIndexUseCase {
    override fun invoke(songIndex: String): Flow<Song?> =
        songRepository.getSongBySongNumber(songIndex)

}