package am.betel.songs.domain.model

import android.text.Html

data class Song(
    val id: Int = 0,
    val songNumber: String = "0",
    val songWords: String = "Error",
) {
    fun getWords() = Html.fromHtml(this.songWords, Html.FROM_HTML_MODE_COMPACT).toString()
}