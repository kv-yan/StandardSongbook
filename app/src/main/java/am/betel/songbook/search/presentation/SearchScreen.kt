package am.betel.songbook.search.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.Blue700
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
            containerColor = Color.White,
            topBar = {
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
                            text = stringResource(R.string.search),
                            fontFamily = FontRegular,
                            color = Blue700
                        )
                    })
            }
        ) { innerPadding ->
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
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Blue700,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedTrailingIconColor = Color.Black,
                        unfocusedTrailingIconColor = Color.LightGray,
                        cursorColor = Blue700,
                        focusedLabelColor = Blue700,
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
                                contentColor = if (searchQuery.isNotEmpty()) Blue700 else Color.LightGray,
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
                                    color = Color.Black,
                                    fontSize = 18.sp
                                )
                            ) {
                                append(searchQuery)
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            ) {
                                append(stringResource(R.string.nothing_founded))
                            }
                        },
                        style = TextStyle(
                            fontFamily = FontRegular,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .border(0.5.dp, Color.LightGray, shape = Shape16)
                            .clip(Shape16)
                    ) {
                        items(foundedSongs, key = { it.id }) {
                            SearchSongItem(
                                song = it, onClick = navigateToDetails
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
                color = Blue700,
                strokeWidth = 4.dp
            )
        }
    }
}