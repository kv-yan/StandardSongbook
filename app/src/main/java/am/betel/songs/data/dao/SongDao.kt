package am.betel.songs.data.dao

import am.betel.songs.data.entity.SongEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songEntities: List<SongEntity>)

    @Query("SELECT * FROM SongEntity")
    suspend fun getAllSongs(): List<SongEntity>

    @Query("SELECT * FROM SongEntity WHERE id = :id")
    suspend fun getSongById(id: Int): SongEntity?
}
