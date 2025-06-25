package am.betel.songbook

import am.betel.songbook.list.presentation.di.songListPresentationModule
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