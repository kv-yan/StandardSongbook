package am.betel.songbook.bookmark.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
    navigateToDetails: (String) -> Unit = {},
) {

    val songs by viewModel.favoriteSongs.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = null,
                            tint = Blue700
                        )
                    }
                },
                title = {
                    Text(
                        text = "Էջանշված երգեր",
                        fontFamily = FontRegular,
                        color = Blue700
                    )
                }
            )
        }
    ) {
        it

        Text(
            text = "$songs",
        )
    }
}