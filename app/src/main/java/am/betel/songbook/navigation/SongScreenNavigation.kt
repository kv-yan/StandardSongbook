package am.betel.songbook.navigation

import am.betel.settings.domain.model.UISettings
import am.betel.songbook.bookmark.presentation.BookmarkScreen
import am.betel.songbook.common.presentation.component.snackbar.SnackbarState
import am.betel.songbook.list.presentation.ListScreen
import am.betel.songbook.navigation.bottom_bar.BottomNavigationBar
import am.betel.songbook.search.presentation.SearchScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun SongScreenNavigation(
    modifier: Modifier = Modifier,
    uiSettings: UISettings,
    onSnackbarShown: (SnackbarState) -> Unit = {},
    navigateToDetails: (Int) -> Unit = {},
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        containerColor = uiSettings.backgroundColor,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                uiSettings = uiSettings
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = SongsDestination.List
        ) {
            composable<SongsDestination.List>(
                enterTransition = null,
                exitTransition = null,
                popEnterTransition = null,
                popExitTransition = null
            ) {
                ListScreen(
                    uiSettings = uiSettings,
                    navigateToDetails = navigateToDetails
                )
            }

            composable<SongsDestination.Search> {
                SearchScreen(
                    navigateToDetails = navigateToDetails,
                    uiSettings = uiSettings,
                    onBackClick = { navController.popBackStack() },
                )
            }

            composable<SongsDestination.Bookmark> {
                BookmarkScreen(
                    uiSettings = uiSettings,
                    navigateToDetails = navigateToDetails,
                    onBackClick = { navController.navigateUp() },
                    onSnackbarShown = onSnackbarShown
                )
            }
        }
    }
}