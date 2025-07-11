package am.betel.settings.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,
    appTheme: AppTheme,
    themes: List<AppTheme>,
    currentFontSize: Float = 16f,
    onFontSizeIncrease: () -> Unit = {},
    onFontSizeDecrease: () -> Unit = {},
    onThemeChange: (AppTheme) -> Unit = {},
) {

    val sheetState = rememberModalBottomSheetState()

    if (expanded.value) ModalBottomSheet(
        modifier = modifier,
        containerColor = appTheme.backgroundColor,
        sheetState = sheetState,
        onDismissRequest = { expanded.value = false }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {

            Text(
                modifier = Modifier.padding(
                    start = 16.dp, bottom = 16.dp
                ),
                text = stringResource(R.string.change_font_size),
                color = appTheme.primaryTextColor,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FontSizeController(
                    modifier = Modifier.weight(1f), appTheme = appTheme, fontSize = 12.sp
                ) {
                    onFontSizeDecrease()
                }

                Text(
                    text = currentFontSize.toInt().toString(),
                    fontSize = 16.sp,
                    color = appTheme.primaryColor
                )

                FontSizeController(
                    modifier = Modifier.weight(1f), appTheme = appTheme, fontSize = 18.sp
                ) {
                    onFontSizeIncrease()
                }
            }

            ThemeController(
                modifier = Modifier.fillMaxWidth(),
                currentTheme = appTheme,
                availableThemes = themes,
                onClick = onThemeChange
            )
        }
    }
}
