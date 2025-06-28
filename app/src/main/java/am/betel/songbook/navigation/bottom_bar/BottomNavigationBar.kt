package am.betel.songbook.navigation.bottom_bar


import am.betel.settings.domain.model.UISettings
import am.betel.songbook.common.presentation.ui.theme.Blue700
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController,
    uiSettings: UISettings
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
        backgroundColor = uiSettings.backgroundColor,
    ) {
        items.forEach { item ->
            val isSelected = item.route::class.qualifiedName?.let {
                currentDestination?.route?.contains(it)
            } ?: false
            BottomNavigationItem(
                selectedContentColor = uiSettings.primaryTextColor,
                unselectedContentColor = uiSettings.unfocusedColor,
                selected = isSelected,
                label = {
                    Text(
                        text = item.route.route,
                        color = if (isSelected) uiSettings.primaryColor else uiSettings.secondaryTextColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconResource),
                        contentDescription = null,
                        tint = if (isSelected) uiSettings.primaryColor else uiSettings.secondaryTextColor
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
