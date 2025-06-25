package am.betel.songbook.details.data.di

import am.betel.songbook.details.data.usecase.GetSongByIndexUseCaseImpl
import am.betel.songbook.details.domain.usecase.GetSongByIndexUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailsDataModule = module {
    factoryOf(::GetSongByIndexUseCaseImpl) { bind<GetSongByIndexUseCase>() }
}