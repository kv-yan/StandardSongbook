package am.betel.songbook.list.presentation

import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songbook.common.presentation.ui.theme.Shape16
import am.betel.songbook.list.presentation.components.ListRageItem
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    songbookListViewModel: SongbookListViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit = {},
) {
    val songsIntRange by songbookListViewModel.songsCount.collectAsState()
    val searchQuery by songbookListViewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(top = 8.dp, bottom = 52.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = searchQuery,
                shape = Shape16,
                onValueChange = songbookListViewModel::setSearchQuery,
                singleLine = true,
                textStyle = TextStyle(fontFamily = FontRegular),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    navigateToDetails(searchQuery.toInt())
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
                        text = "Գրեք համարը",
                        style = TextStyle(fontFamily = FontRegular)
                    )
                },
                trailingIcon = {
                    TextButton(
                        enabled = searchQuery.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (searchQuery.isNotEmpty()) Blue700 else Color.LightGray,
                        ),
                        onClick = { navigateToDetails(searchQuery.toInt()) }) {
                        Text("Գտնել")
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(16.dp)),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(
                    items = songsIntRange,
                    key = { index, _ -> index }
                ) { index, range ->
                    ListRageItem(
                        modifier = Modifier.fillMaxWidth(),
                        intRange = range,
                        initialExpanded = false,
                        navigateToDetails = navigateToDetails
                    )
                }
            }
        }
    }
}
