package am.betel.songs.data.helper

import am.betel.songs.data.entity.SongEntity
import am.betel.songs.domain.model.Song

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
    return this.songWords.lineSequence()
        .map { it.trim() }
        .firstOrNull { it.isNotEmpty() }
        ?: ""
}
