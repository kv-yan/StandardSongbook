package am.betel.songbook.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination {
    @Serializable
    data object SongScreen : AppDestination()

    @Serializable
    data class Details(val id: String) : AppDestination()
}