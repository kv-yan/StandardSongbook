package am.betel.songs.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "FavoriteSongs",
    foreignKeys = [ForeignKey(
        entity = SongEntity::class,
        parentColumns = ["id"],
        childColumns = ["songId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["songId"])]
)
data class FavoriteSongEntity(
    @PrimaryKey val songId: Int,
    val addedAt: Long = System.currentTimeMillis()
)
