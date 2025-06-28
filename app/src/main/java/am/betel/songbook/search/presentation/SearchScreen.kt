package am.betel.songbook.search.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songbook.common.presentation.ui.theme.Shape16
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    viewModel: SearchViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val foundedSongs by viewModel.foundedSongs.collectAsState()
    val nothingFounded by viewModel.nothingFounded.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = appTheme.backgroundColor,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = appTheme.backgroundColor
                    ), navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_back),
                                contentDescription = null,
                                tint = appTheme.primaryColor
                            )
                        }
                    }, title = {
                        Text(
                            text = stringResource(R.string.search),
                            fontFamily = FontRegular,
                            color = appTheme.primaryColor
                        )
                    })
            }) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = searchQuery,
                    onValueChange = viewModel::setSearchQuery,
                    shape = Shape16,
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = FontRegular),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text,
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.onSearchClick()
                    }),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = appTheme.primaryTextColor,
                        unfocusedTextColor = appTheme.primaryTextColor,
                        focusedContainerColor = appTheme.backgroundColor,
                        unfocusedContainerColor = appTheme.backgroundColor,
                        focusedIndicatorColor = appTheme.primaryColor,
                        unfocusedIndicatorColor = appTheme.unfocusedColor,
                        focusedTrailingIconColor = appTheme.unfocusedColor,
                        unfocusedTrailingIconColor = appTheme.unfocusedColor,
                        focusedLabelColor = appTheme.primaryColor,
                        unfocusedLabelColor = appTheme.primaryTextColor,
                        cursorColor = appTheme.primaryColor,

                    ),
                    label = {
                        Text(
                            text = stringResource(R.string.search_by_words),
                            style = TextStyle(fontFamily = FontRegular)
                        )
                    },
                    trailingIcon = {
                        TextButton(
                            enabled = searchQuery.isNotEmpty(),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = if (searchQuery.isNotEmpty()) appTheme.primaryColor else appTheme.unfocusedColor,
                                disabledContentColor = appTheme.unfocusedColor
                            ),
                            onClick = { viewModel.onSearchClick() }) {
                            Text(text = stringResource(R.string.search))
                        }
                    })
                if (nothingFounded) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = appTheme.primaryTextColor,
                                    fontSize = 18.sp
                                )
                            ) {
                                append(searchQuery)
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = appTheme.secondaryTextColor,
                                    fontSize = 16.sp
                                )
                            ) {
                                append(stringResource(R.string.nothing_founded))
                            }
                        },
                        style = TextStyle(
                            fontFamily = FontRegular,
                            color = appTheme.secondaryTextColor,
                            textAlign = TextAlign.Center
                        )
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .border(0.5.dp, appTheme.unfocusedColor, shape = Shape16)
                            .clip(Shape16)
                    ) {
                        items(foundedSongs, key = { it.id }) {
                            SearchSongItem(
                                modifier = Modifier.padding(vertical = 8.dp),
                                song = it,
                                appTheme = appTheme,
                                onClick = navigateToDetails
                            )
                        }
                    }
                }
            }
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = appTheme.primaryColor, strokeWidth = 4.dp
            )
        }
    }
}