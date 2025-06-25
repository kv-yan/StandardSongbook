package am.betel.songs.domain.repository

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getSongBySongNumber(songNumber: String): Flow<Song?>

    fun searchSongsByText(query: String): Flow<List<Song>>

    fun getFavoriteSongs(): Flow<List<Song>>

    suspend fun addToFavorites(song: Song)

    suspend fun removeFromFavorites(song: Song)

    fun isFavorite(song: Song): Flow<Boolean>

}