package am.betel.songs.data.repository

import am.betel.songs.data.dao.SongDao
import am.betel.songs.data.helper.toSong
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SongRepositoryImpl(
    private val songDao: SongDao,
) : SongRepository {

    override fun getSongBySongNumber(songNumber: String): Flow<Song?> = flow {
        emit(songDao.getSongBySongNumber(songNumber)?.toSong())
    }.flowOn(Dispatchers.IO)

    override fun searchSongsByText(query: String): Flow<List<Song>> = flow {
        emit(songDao.searchSongsByText(query).map { it.toSong() })
    }.flowOn(Dispatchers.IO)
}