package am.betel.songbook.details.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.FontBold
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songs.data.helper.getTitle
import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Settings
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    index: String,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = getViewModel { parametersOf(index) },
    onBackClick: () -> Unit = {},
) {
    val currentSongs by viewModel.currentSongs.collectAsState()


    val verticalScrollState = rememberScrollState()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = "Երգ ${currentSongs?.songNumber}",
                        fontFamily = FontBold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 20.sp,
                        color = Blue700
                    )

                },
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
                actions = {

                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = Blue700
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = null,
                            tint = Blue700
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 24.dp, end = 4.dp)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = currentSongs?.getTitle() ?: "Error",
                fontFamily = FontRegular,
                fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            val words =
                Html.fromHtml(currentSongs?.songWords, Html.FROM_HTML_MODE_COMPACT)
                    .toString()

            Text(
                words,
                fontFamily = FontRegular,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}