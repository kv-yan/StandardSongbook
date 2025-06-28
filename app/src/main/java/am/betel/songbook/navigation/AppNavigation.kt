package am.betel.songbook.navigation

import am.betel.settings.presentation.SettingsViewModel
import am.betel.songbook.common.presentation.component.snackbar.AppSnackbar
import am.betel.songbook.common.presentation.component.snackbar.SnackbarState
import am.betel.songbook.details.presentation.DetailsScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.SongScreen,
    settingsViewModel: SettingsViewModel = koinViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()
    val snackBars = remember { mutableStateListOf<SnackbarState>() }
    val uiSettings by settingsViewModel.uiSettings.collectAsState()


    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        uiSettings?.let { theme ->
            LaunchedEffect(theme) {
                println("theme changed $theme")
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = theme.darkIcons
                )

                systemUiController.setNavigationBarColor(
                    color = Color.Transparent,
                    darkIcons = theme.darkIcons
                )

            }

            NavHost(
                modifier = modifier.background(theme.backgroundColor),
                navController = navController,
                startDestination = startDestination
            ) {
                composable<AppDestination.SongScreen>(
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null
                ) {
                    SongScreenNavigation(
                        appTheme = theme, onSnackbarShown = { snackBars.add(it) }) {
                        if (it <= 1000) {
                            navController.navigate(AppDestination.Details(it))
                        } else {
                            snackBars.add(
                                SnackbarState.Error(
                                    _message = am.betel.songbook.R.string.no_song_found_by_number
                                )
                            )
                        }
                    }
                }


                composable<AppDestination.Details>(
                    enterTransition = null,
                    exitTransition = null,
                    popEnterTransition = null,
                    popExitTransition = null
                ) { stackEntry ->
                    val index = stackEntry.toRoute<AppDestination.Details>()
                    DetailsScreen(
                        index = index.id,
                        appTheme = theme,
                        settingsViewModel = settingsViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onSnackbarShowed = { snackBars.add(it) })
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
            ) {
                snackBars.forEachIndexed { index, state ->
                    AppSnackbar(
                        modifier = Modifier
                            .offset(y = (index * 8).dp)
                            .zIndex(index.toFloat()),
                        theme = theme,
                        state = state
                    ) {
                        snackBars.remove(state)
                    }
                }
            }
        }
    }
}

