package am.betel.songs.presentation.worker

import am.betel.songs.data.database.AppDatabase
import am.betel.songs.data.entity.SongEntity
import android.content.Context
import android.os.Build
import android.text.Html
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImportSongsWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        println("Worker started")
        return try {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "songs-db"
            ).build()

            val songDao = db.songDao()

            val inputStream = applicationContext.assets.open("songs.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val gson = Gson()
            val type = object : TypeToken<List<SongRaw>>() {}.type
            val rawSongs: List<SongRaw> = gson.fromJson(json, type)

            val cleanSongs = rawSongs.map {
                SongEntity(
                    songNumber = it.songNumber,
                    songWords = cleanHtmlText(it.songWords)
                )
            }

            songDao.insertAll(cleanSongs)
            println(
                "Imported ${cleanSongs.size} songs"
            )

            Result.success()
        } catch (e: Exception) {
            println("Error importing songs: ${e}")
            Result.failure()
        }
    }

    private fun cleanHtmlText(input: String): String {
        val html = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(input)
        }
        return html.toString().trim()
    }

    data class SongRaw(
        val songNumber: String,
        val songWords: String
    )
}
