package am.betel.songbook.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SongsDestination(val route: String) {
    @Serializable
    data object List : SongsDestination("Ցանկ")

    @Serializable
    data object Search : SongsDestination("Որոնում")

    @Serializable
    data object Bookmark : SongsDestination("Էջանշված")
}

