package am.betel.songbook.bookmark.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.component.snackbar.SnackbarState
import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = koinViewModel(),
    onSnackbarShown: (SnackbarState) -> Unit = {},
    onBackClick: () -> Unit = {},
    navigateToDetails: (Int) -> Unit = {},
) {

    val songs by viewModel.favoriteSongs.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(), containerColor = Color.White, topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ), navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = null,
                            tint = Blue700
                        )
                    }
                }, title = {
                    Text(
                        text = stringResource(R.string.bookmarked_songs),
                        fontFamily = FontRegular,
                        color = Blue700
                    )
                })
        }) { innerPadding ->
        if (songs.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(R.string.no_bookmarked_songs),
                fontFamily = FontRegular,
                color = Blue700,
                fontSize = 24.sp,
                textAlign = TextAlign.Center

            )

        } else LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 64.dp),
        ) {
            items(
                items = songs,
                key = { it.id }
            ) { song ->
                BookmarkedSongItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    song = song,
                    onClick = { navigateToDetails(it) },
                    onRemoveClick = {
                        viewModel.removeFavoriteSong(
                            song = song,
                            showSnackbar = onSnackbarShown
                        )
                    }
                )
            }
        }
    }
}