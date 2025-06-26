package am.betel.settings.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.Shape10
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit

@Composable
fun FontSizeController(
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue700,
            contentColor = Color.White
        ),
        shape = Shape10
    ) {
        Text(
            text = stringResource(R.string.font_size_controller_text),
            fontSize = fontSize
        )
    }
}
