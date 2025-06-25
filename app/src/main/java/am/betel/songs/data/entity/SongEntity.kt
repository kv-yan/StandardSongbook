package am.betel.songs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val songNumber: String,
    val songWords: String
)
