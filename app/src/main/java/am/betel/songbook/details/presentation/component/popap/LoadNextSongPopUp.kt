package am.betel.songbook.details.presentation.component.popap

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.R
import am.betel.songbook.common.presentation.component.inputFeald.AppInputField
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun LoadNextSongDialog(
    modifier: Modifier = Modifier,
    theme: AppTheme,
    onDismiss: () -> Unit = {},
    onConfirm: (Int) -> Unit = {},
) {
    var input by remember { mutableStateOf("") }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        containerColor = theme.backgroundColor,
        titleContentColor = theme.primaryColor,

        title = {
            Text(
                text = stringResource(R.string.enter_next_song_index),
                fontFamily = FontRegular,
                fontSize = 18.sp,
            )
        },
        text = {
            AppInputField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.search_by_words),
                searchQuery = input,
                appTheme = theme,
                onValueChange = { input = it },
                trailingIcon = {
                    TextButton(
                        enabled = input.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (input.isNotEmpty()) theme.primaryColor else theme.unfocusedColor,
                            disabledContentColor = theme.unfocusedColor
                        ),
                        onClick = { onConfirm(input.toInt()) }) {
                        Text(text = stringResource(R.string.search))
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onConfirm(input.toInt())
                    }
                )
            )
        },
        confirmButton = {},
    )
}
