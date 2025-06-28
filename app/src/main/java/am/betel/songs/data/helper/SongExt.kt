package am.betel.songs.data.helper

import am.betel.songs.data.entity.FavoriteSongEntity
import am.betel.songs.data.entity.SongEntity
import am.betel.songs.domain.model.Song
import android.text.Html

fun SongEntity.toSong(): Song {
    return Song(
        id = id,
        songNumber = songNumber,
        songWords = songWords
    )
}

fun Song.toSongEntity(): SongEntity {
    return SongEntity(
        id = id,
        songNumber = songNumber,
        songWords = songWords
    )
}

fun Song.toFavoriteSongEntity(): FavoriteSongEntity {
    return FavoriteSongEntity(
        songId = id
    )
}

/*
fun Song.getTitle(): String {

    val text = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
    return text.lineSequence()
        .map { it.trim() }
        .firstOrNull { it.isNotEmpty() }
        ?: ""
}
*/

fun Song.getTitle(): String {

    val text = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
    val result = StringBuilder()

    for (it in text.split("\n")) {
        if (it.contains("1.")) {
            result.append(it)
        }
    }


    return result.toString()
}


fun List<SongEntity>.toSong(): List<Song> {
    return this.map { it.toSong() }
}
