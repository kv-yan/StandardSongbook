package am.betel.songbook.navigation

import am.betel.songbook.bookmark.presentation.BookmarkScreen
import am.betel.songbook.common.presentation.ui.theme.ScreenBackground
import am.betel.songbook.list.presentation.ListScreen
import am.betel.songbook.navigation.bottom_bar.BottomNavigationBar
import am.betel.songbook.search.presentation.SearchScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SongScreenNavigation(
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit = {},
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        containerColor = ScreenBackground,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = SongsDestination.List
        ) {
            composable<SongsDestination.List> {
                ListScreen(navigateToDetails = navigateToDetails)
            }
            composable<SongsDestination.Search> { SearchScreen() }
            composable<SongsDestination.Saved> {
                BookmarkScreen(
                    navigateToDetails = navigateToDetails,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        it
    }
}