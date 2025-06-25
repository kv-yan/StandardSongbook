package am.betel.songs.domain.repository

import am.betel.songs.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getSongBySongNumber(songNumber: String): Flow<Song?>

    fun searchSongsByText(query: String): Flow<List<Song>>
}