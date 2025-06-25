package am.betel.songbook.details.presentation.di

import am.betel.songbook.details.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsPresentationModule = module {
    viewModel { (songIndex: String) ->
        DetailsViewModel(
            songIndex = songIndex,
            getSongByIndexUseCase = get(),
            addToFavoritesUseCaseImpl = get(),
            removeFromFavoritesUseCase = get(),
            isFavoriteUseCase = get()
        )
    }
}