package am.betel.songs.data.repository

import am.betel.songs.data.dao.FavoriteSongDao
import am.betel.songs.data.dao.SongDao
import am.betel.songs.data.helper.getTitle
import am.betel.songs.data.helper.toFavoriteSongEntity
import am.betel.songs.data.helper.toSong
import am.betel.songs.domain.model.Song
import am.betel.songs.domain.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SongRepositoryImpl(
    private val songDao: SongDao,
    private val favoriteSongDao: FavoriteSongDao,
) : SongRepository {

    override fun getSongBySongNumber(songNumber: String): Flow<Song?> = flow {
        emit(songDao.getSongBySongNumber(songNumber)?.toSong())
    }.flowOn(Dispatchers.IO)

    override fun searchSongsByText(query: String): Flow<List<Song>> = flow {
        emit(songDao.searchSongsByText(query).map { it.toSong() })
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteSongs(): Flow<List<Song>> = flow {
        emit(favoriteSongDao.getFavoriteSongs().toSong())
    }.flowOn(Dispatchers.IO)

    override  suspend fun addToFavorites(song: Song) {
        favoriteSongDao.addToFavorites(song.toFavoriteSongEntity())
    }

    override suspend fun removeFromFavorites(song: Song) {
        favoriteSongDao.removeFromFavorites(song.toFavoriteSongEntity())
    }

    override fun isFavorite(song: Song): Flow<Boolean> = flow {
       val result = favoriteSongDao.isFavorite(song.id)
        println("is favorite $result :: ${song.getTitle()}")
        emit(result)
    }.flowOn(Dispatchers.IO)
}