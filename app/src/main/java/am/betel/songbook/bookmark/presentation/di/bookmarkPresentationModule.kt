package am.betel.songbook.bookmark.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import am.betel.songbook.bookmark.presentation.BookmarkViewModel


val bookmarkPresentationModule = module {
    viewModelOf(::BookmarkViewModel)
}