package am.betel.songbook.bookmark.data.usecase

import am.betel.songbook.bookmark.domain.usecase.AddToFavoritesUseCase
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository

class AddToFavoritesUseCaseImpl(
    private val songRepository: SongRepository
) : AddToFavoritesUseCase {
    override suspend fun invoke(song: Song) = songRepository.addToFavorites(song)
}