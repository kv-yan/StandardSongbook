package am.betel.songbook.list.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import am.betel.songbook.list.presentation.SongbookListViewModel

val songListPresentationModule = module {
    viewModelOf(::SongbookListViewModel)
}