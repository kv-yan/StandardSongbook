package am.betel.songbook.bookmark.data.di

import am.betel.songbook.bookmark.data.usecase.AddToFavoritesUseCaseImpl
import am.betel.songbook.bookmark.data.usecase.GetFavoriteSongsUseCaseImpl
import am.betel.songbook.bookmark.data.usecase.IsFavoriteUseCaseImpl
import am.betel.songbook.bookmark.data.usecase.RemoveFromFavoritesUseCaseImpl
import am.betel.songbook.bookmark.domain.usecase.AddToFavoritesUseCase
import am.betel.songbook.bookmark.domain.usecase.GetFavoriteSongsUseCase
import am.betel.songbook.bookmark.domain.usecase.IsFavoriteUseCase
import am.betel.songbook.bookmark.domain.usecase.RemoveFromFavoritesUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val bookmarkDataModule = module {
    singleOf(::AddToFavoritesUseCaseImpl) { bind<AddToFavoritesUseCase>() }
    singleOf(::GetFavoriteSongsUseCaseImpl) { bind<GetFavoriteSongsUseCase>() }
    singleOf(::RemoveFromFavoritesUseCaseImpl) { bind<RemoveFromFavoritesUseCase>() }
    singleOf(::IsFavoriteUseCaseImpl) { bind<IsFavoriteUseCase>() }
}