package am.betel.songs.data.di

import am.betel.songs.data.dao.SongDao
import am.betel.songs.data.database.AppDatabase
import am.betel.songs.data.repository.SongRepositoryImpl
import am.betel.songs.domain.repository.SongRepository
import android.content.Context
import androidx.room.Room
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val songsDataModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            get<Context>(),
            AppDatabase::class.java, "songs-db"
        ).build()
    }

    single<SongDao> {
        get<AppDatabase>().songDao()
    }

    singleOf(::SongRepositoryImpl) { bind<SongRepository>() }
}