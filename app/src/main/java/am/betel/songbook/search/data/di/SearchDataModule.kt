package am.betel.songbook.search.data.di

import am.betel.songbook.search.data.usecase.SearchSongByTextUseCaseImpl
import am.betel.songbook.search.domain.usecase.SearchSongByTextUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val searchDataModule = module {
    factoryOf(::SearchSongByTextUseCaseImpl){bind<SearchSongByTextUseCase>()}
}