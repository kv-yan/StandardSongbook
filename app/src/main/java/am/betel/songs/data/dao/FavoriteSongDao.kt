package am.betel.songs.data.dao

import am.betel.songs.data.entity.FavoriteSongEntity
import am.betel.songs.data.entity.SongEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteSongDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(favorite: FavoriteSongEntity)

    @Delete
    suspend fun removeFromFavorites(favorite: FavoriteSongEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteSongs WHERE songId = :songId)")
    suspend fun isFavorite(songId: Int): Boolean

    @Query(
        """
        SELECT s.* FROM SongEntity s
        INNER JOIN FavoriteSongs f ON s.id = f.songId ORDER BY f.addedAt
    """
    )
    fun getFavoriteSongs(): Flow<List<SongEntity>>
}
