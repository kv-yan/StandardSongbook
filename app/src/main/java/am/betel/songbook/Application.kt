package am.betel.songbook

import am.betel.settings.data.di.settingsDataModule
import am.betel.settings.presentation.di.settingsPresentationModule
import am.betel.share.data.di.shareDataModule
import am.betel.songbook.bookmark.data.di.bookmarkDataModule
import am.betel.songbook.bookmark.presentation.di.bookmarkPresentationModule
import am.betel.songbook.details.data.di.detailsDataModule
import am.betel.songbook.details.presentation.di.detailsPresentationModule
import am.betel.songbook.list.presentation.di.songListPresentationModule
import am.betel.songbook.search.data.di.searchDataModule
import am.betel.songbook.search.presentation.di.searchPresentationModule
import am.betel.songs.data.di.songsDataModule
import am.betel.songs.presentation.worker.ImportSongsWorker
import android.app.Application
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.concurrent.TimeUnit

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                songListPresentationModule,
                detailsDataModule,
                detailsPresentationModule,
                songsDataModule,
                bookmarkPresentationModule,
                bookmarkDataModule,
                searchPresentationModule,
                searchDataModule,
                settingsPresentationModule,
                settingsDataModule,
                shareDataModule,
            )
        }

        val workRequest = OneTimeWorkRequestBuilder<ImportSongsWorker>()
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                10,
                TimeUnit.SECONDS
            )
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "import_songs_once",
            ExistingWorkPolicy.KEEP, // ensures it runs only once
            workRequest
        )
    }
}