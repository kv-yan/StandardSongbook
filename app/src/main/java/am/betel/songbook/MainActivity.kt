package am.betel.songbook

import am.betel.songbook.navigation.AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            LaunchedEffect(Unit) {
                // Change only the bottom navigation bar color
                systemUiController.setNavigationBarColor(
                    color = Color.White, // Your desired color
                    darkIcons = true // Automatically adjust icon colors
                )

                // Optional: Keep status bar settings if needed
                systemUiController.setStatusBarColor(
                    color = Color.White,
                    darkIcons = true
                )
            }
            AppNavigation()
        }
    }
}