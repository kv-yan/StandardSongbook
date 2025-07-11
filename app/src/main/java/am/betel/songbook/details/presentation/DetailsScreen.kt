package am.betel.songbook.details.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.settings.presentation.SettingsBottomSheet
import am.betel.settings.presentation.SettingsViewModel
import am.betel.songbook.R
import am.betel.songbook.common.presentation.component.snackbar.SnackbarState
import am.betel.songbook.common.presentation.ui.theme.FontBold
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songbook.details.presentation.component.popap.LoadNextSongDialog
import am.betel.songs.data.helper.getTitle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    index: Int,
    appTheme: AppTheme,
    viewModel: DetailsViewModel = getViewModel { parametersOf(index) },
    settingsViewModel: SettingsViewModel = koinViewModel(),
    onSnackbarShowed: (SnackbarState) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val currentSongs by viewModel.currentSongs.collectAsState()
    val currentFontSize by settingsViewModel.fontSize.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    var isLoadSongDialogVisible by remember { mutableStateOf(false) }
    val bottomSheetExpanded = remember { mutableStateOf(false) }
    val themes by settingsViewModel.availableThemes.collectAsState()
    val context = LocalContext.current

    val verticalScrollState = rememberScrollState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = appTheme.backgroundColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appTheme.backgroundColor
                ), title = {
                    Text(
                        text = stringResource(R.string.song_number, currentSongs?.songNumber ?: 0),
                        fontFamily = FontBold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 20.sp,
                        color = appTheme.primaryColor
                    )

                }, navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }
                }, actions = {

                    IconButton(
                        onClick = {
                            isLoadSongDialogVisible = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_load_next_song),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.shareSong(context)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Share,
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.toggleFavorite(onSnackbarShowed = onSnackbarShowed)
                        }
                    ) {
                        Icon(
                            painter = if (isFavorite)
                                painterResource(R.drawable.ic_bookmark_remove)
                            else
                                painterResource(R.drawable.ic_bookmark_add),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = { bottomSheetExpanded.value = true }) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }
                })
        },
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
                text = currentSongs?.getTitle() ?: stringResource(R.string.error_text),
                fontFamily = FontRegular,
                fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = appTheme.primaryTextColor
            )

            currentSongs?.getWords()?.let { it1 ->
                SwipeableSongText(
                    modifier = modifier,
                    words = it1,
                    appTheme = appTheme,
                    fontSize = currentFontSize,
                    onNextSong = viewModel::loadNextSong,
                    onPrevSong = viewModel::loadPrevSong
                )
            }
        }
    }

    SettingsBottomSheet(
        expanded = bottomSheetExpanded,
        appTheme = appTheme,
        themes = themes,
        currentFontSize = currentFontSize,
        onFontSizeIncrease = settingsViewModel::increment,
        onFontSizeDecrease = settingsViewModel::decrement,
        onThemeChange = settingsViewModel::setUiSetting
    )

    if (isLoadSongDialogVisible) {
        LoadNextSongDialog(
            theme = appTheme,
            onDismiss = { isLoadSongDialogVisible = false },
            onConfirm = {
                viewModel.loadSongByIndex(
                    index = it,
                    onSnackbarShowed = onSnackbarShowed,
                    onLoadComplete = {
                        isLoadSongDialogVisible = false
                    }
                )
            }
        )
    }
}