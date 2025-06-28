package am.betel.songbook.navigation

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.bookmark.presentation.BookmarkScreen
import am.betel.songbook.common.presentation.component.snackbar.SnackbarState
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
    appTheme: AppTheme,
    onSnackbarShown: (SnackbarState) -> Unit = {},
    navigateToDetails: (Int) -> Unit = {},
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        containerColor = appTheme.backgroundColor,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                appTheme = appTheme
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
                    appTheme = appTheme,
                    navigateToDetails = navigateToDetails
                )
            }

            composable<SongsDestination.Search> {
                SearchScreen(
                    navigateToDetails = navigateToDetails,
                    appTheme = appTheme,
                    onBackClick = { navController.popBackStack() },
                )
            }

            composable<SongsDestination.Bookmark> {
                BookmarkScreen(
                    appTheme = appTheme,
                    navigateToDetails = navigateToDetails,
                    onBackClick = { navController.navigateUp() },
                    onSnackbarShown = onSnackbarShown
                )
            }
        }
    }
}