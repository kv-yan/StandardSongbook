package am.betel.songbook

import am.betel.songbook.list.presentation.di.songListPresentationModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                songListPresentationModule,
            )
        }
    }
}