package am.betel.songbook.details.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwipeableSongText(
    words: String,
    appTheme: AppTheme,
    fontSize: Float = 16f,
    onNextSong: () -> Unit,
    onPrevSong: () -> Unit,
) {
    var totalDrag by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .heightIn(min = 200.dp, max = 1500.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragEnd = {
                    when {
                        totalDrag > 100 -> onPrevSong()
                        totalDrag < -100 -> onNextSong()
                    }
                    totalDrag = 0f
                }, onHorizontalDrag = { change, dragAmount ->
                    change.consume()
                    totalDrag += dragAmount
                })
            }
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 40.dp
            )
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = words,
            fontFamily = FontRegular,
            fontStyle = FontStyle.Normal,
            fontSize = fontSize.sp,
            color = appTheme.primaryTextColor
        )
    }
}
