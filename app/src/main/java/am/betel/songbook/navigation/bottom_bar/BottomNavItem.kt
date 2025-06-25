package am.betel.songbook.navigation.bottom_bar

import am.betel.songbook.R
import am.betel.songbook.navigation.SongsDestination

sealed class BottomNavItem(val iconResource: Int, val route: SongsDestination) {
    data object HomeScreen : BottomNavItem(
        iconResource = R.drawable.ic_list, route = SongsDestination.List
    )

    data object ShopScreen : BottomNavItem(
        iconResource = R.drawable.ic_bookmark, route = SongsDestination.Bookmark
    )

    data object AddEventScreen : BottomNavItem(
        iconResource = R.drawable.ic_search, route = SongsDestination.Search
    )
}