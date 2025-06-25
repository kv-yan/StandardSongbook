package am.betel.songbook.bookmark.domain.usecase

import am.betel.songs.domain.model.Song

interface RemoveFromFavoritesUseCase {
    suspend operator fun invoke(song: Song)

}