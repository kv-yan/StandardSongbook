package am.betel.songbook.list.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.R
import am.betel.songbook.common.presentation.component.inputFeald.AppInputField
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    songbookListViewModel: SongbookListViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit = {},
) {
    val songsIntRange by songbookListViewModel.songsCount.collectAsState()
    val searchQuery by songbookListViewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = appTheme.backgroundColor,
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
            AppInputField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.enter_number),
                searchQuery = searchQuery,
                appTheme = appTheme,
                onValueChange = songbookListViewModel::setSearchQuery,
                trailingIcon = {
                    TextButton(
                        enabled = searchQuery.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (searchQuery.isNotEmpty()) appTheme.primaryColor else appTheme.unfocusedColor,
                            disabledContentColor = appTheme.unfocusedColor
                        ),
                        onClick = { navigateToDetails(searchQuery.toInt()) }) {
                        Text(stringResource(R.string.search))
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    navigateToDetails(searchQuery.toInt())
                })

            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = appTheme.backgroundColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(0.5.dp, appTheme.unfocusedColor, RoundedCornerShape(16.dp)),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(
                    items = songsIntRange,
                    key = { index, _ -> index }
                ) { _, range ->
                    ListRageItem(
                        modifier = Modifier.fillMaxWidth(),
                        intRange = range,
                        appTheme = appTheme,
                        initialExpanded = false,
                        navigateToDetails = navigateToDetails
                    )
                }
            }
        }
    }
}
