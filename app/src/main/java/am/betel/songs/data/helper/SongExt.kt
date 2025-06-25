package am.betel.songs.data.helper

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

fun Song.getTitle(): String {

    val text = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
    return text.lineSequence()
        .map { it.trim() }
        .firstOrNull { it.isNotEmpty() }
        ?: ""
}
