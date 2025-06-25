package am.betel.songbook.navigation

import am.betel.songbook.details.presentation.DetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.SongScreen,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable<AppDestination.SongScreen>(
            enterTransition = null,
            exitTransition = null,
            popEnterTransition = null,
            popExitTransition = null
        ) {
            SongScreenNavigation {
                println("clicked on $it")
                navController.navigate(AppDestination.Details(it))
            }
        }

        composable<AppDestination.Details>(
            enterTransition = null,
            exitTransition = null,
            popEnterTransition = null,
            popExitTransition = null
        ) {
            val index = it.toRoute<AppDestination.Details>()
            DetailsScreen(
                index = index.id, onBackClick = {
                    navController.popBackStack()
                })
        }
    }
}

