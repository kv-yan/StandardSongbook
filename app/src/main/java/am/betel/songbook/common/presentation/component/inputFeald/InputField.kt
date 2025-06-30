package am.betel.songbook.common.presentation.component.inputFeald

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songbook.common.presentation.ui.theme.Shape16
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun AppInputField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    appTheme: AppTheme,
    label: String = stringResource(R.string.empty_string),
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = searchQuery,
        shape = Shape16,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(fontFamily = FontRegular),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors(
            focusedTextColor = appTheme.primaryTextColor,
            unfocusedTextColor = appTheme.primaryTextColor,
            focusedContainerColor = appTheme.backgroundColor,
            unfocusedContainerColor = appTheme.backgroundColor,
            focusedIndicatorColor = appTheme.primaryColor,
            unfocusedIndicatorColor = appTheme.unfocusedColor,
            focusedTrailingIconColor = appTheme.primaryTextColor,
            unfocusedTrailingIconColor = appTheme.unfocusedColor,
            unfocusedLabelColor = appTheme.primaryTextColor,
            cursorColor = appTheme.primaryColor,
            focusedLabelColor = appTheme.primaryColor,
        ),
        label = {
            Text(
                text = label,
                style = TextStyle(fontFamily = FontRegular),
            )
        },
        trailingIcon = trailingIcon
    )
}