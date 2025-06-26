package am.betel.songbook.search.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import am.betel.songbook.search.presentation.SearchViewModel

val searchPresentationModule = module {
    viewModelOf(::SearchViewModel)
}