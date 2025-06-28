package am.betel.songbook.list.presentation

import am.betel.settings.domain.model.UISettings
import am.betel.songbook.R
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    uiSettings: UISettings,
    songbookListViewModel: SongbookListViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit = {},
) {
    val songsIntRange by songbookListViewModel.songsCount.collectAsState()
    val searchQuery by songbookListViewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = uiSettings.backgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(
                    top = 8.dp,
                    bottom = 52.dp
                )
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
                    focusedTextColor = uiSettings.primaryTextColor,
                    unfocusedTextColor = uiSettings.primaryTextColor,
                    focusedContainerColor = uiSettings.backgroundColor,
                    unfocusedContainerColor = uiSettings.backgroundColor,
                    focusedIndicatorColor = uiSettings.primaryColor,
                    unfocusedIndicatorColor = uiSettings.unfocusedColor,
                    focusedTrailingIconColor = uiSettings.primaryTextColor,
                    unfocusedTrailingIconColor = uiSettings.unfocusedColor,
                    unfocusedLabelColor = uiSettings.primaryTextColor,
                    cursorColor = uiSettings.primaryColor,
                    focusedLabelColor = uiSettings.primaryColor,
                ),
                label = {
                    Text(
                        text = stringResource(R.string.enter_number),
                        style = TextStyle(fontFamily = FontRegular),
                    )
                },
                trailingIcon = {
                    TextButton(
                        enabled = searchQuery.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (searchQuery.isNotEmpty()) uiSettings.primaryColor else uiSettings.unfocusedColor,
                            disabledContentColor = uiSettings.unfocusedColor
                        ),
                        onClick = { navigateToDetails(searchQuery.toInt()) }) {
                        Text(stringResource(R.string.search))
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = uiSettings.backgroundColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(0.5.dp, uiSettings.unfocusedColor, RoundedCornerShape(16.dp)),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(
                    items = songsIntRange,
                    key = { index, _ -> index }
                ) { _, range ->
                    ListRageItem(
                        modifier = Modifier.fillMaxWidth(),
                        intRange = range,
                        uiSettings = uiSettings,
                        initialExpanded = false,
                        navigateToDetails = navigateToDetails
                    )
                }
            }
        }
    }
}
