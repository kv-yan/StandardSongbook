package am.betel.songbook.bookmark.data.usecase

import am.betel.songbook.bookmark.domain.usecase.RemoveFromFavoritesUseCase
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository

class RemoveFromFavoritesUseCaseImpl(
    private val songRepository: SongRepository,
) : RemoveFromFavoritesUseCase {
    override suspend fun invoke(song: Song) = songRepository.removeFromFavorites(song)
}