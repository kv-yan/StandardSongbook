package am.betel.songbook.navigation.bottom_bar


import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.navigation.SongsDestination
import androidx.compose.foundation.layout.systemBarsPadding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController,
    appTheme: AppTheme
) {
    val items = listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.ShopScreen,
        BottomNavItem.AddEventScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier.systemBarsPadding(),
        backgroundColor = appTheme.backgroundColor,
    ) {
        items.forEach { item ->
            val isSelected = item.route::class.qualifiedName?.let {
                currentDestination?.route?.contains(it)
            } ?: false
            BottomNavigationItem(
                selectedContentColor = appTheme.primaryTextColor,
                unselectedContentColor = appTheme.unfocusedColor,
                selected = isSelected,
                label = {
                    Text(
                        text = item.route.route,
                        color = if (isSelected) appTheme.primaryColor else appTheme.secondaryTextColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconResource),
                        contentDescription = null,
                        tint = if (isSelected) appTheme.primaryColor else appTheme.secondaryTextColor
                    )
                },
                onClick = {
                    if (item.route == SongsDestination.List) {
                        navController.navigateUp()
                    } else {
                        navController.navigate(item.route) {
                            popUpTo(SongsDestination.List)
                        }
                    }
                }
            )
        }
    }
}
