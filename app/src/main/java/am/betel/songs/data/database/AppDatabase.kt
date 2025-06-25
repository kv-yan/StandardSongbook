package am.betel.songs.data.database

import am.betel.songs.data.dao.FavoriteSongDao
import am.betel.songs.data.dao.SongDao
import am.betel.songs.data.entity.FavoriteSongEntity
import am.betel.songs.data.entity.SongEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SongEntity::class, FavoriteSongEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun favoriteSongDao(): FavoriteSongDao
}
