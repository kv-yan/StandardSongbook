package am.betel.songbook.bookmark.domain.usecase

import am.betel.songs.domain.model.Song

interface AddToFavoritesUseCase {
    suspend operator fun invoke(song: Song)
}