package am.betel.songbook.navigation

import am.betel.songbook.details.presentation.DetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.Details("15"),
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<AppDestination.SongScreen> {
            SongScreenNavigation {
                navController.navigate(AppDestination.Details(it))
            }
        }

        composable<AppDestination.Details> {
            DetailsScreen(

            )
        }
    }
}

