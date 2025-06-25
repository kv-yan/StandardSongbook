package am.betel.songs.data.database

import am.betel.songs.data.dao.SongDao
import am.betel.songs.data.entity.SongEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SongEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}
