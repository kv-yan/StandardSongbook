package am.betel.songbook.search.presentation

import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songs.domain.model.Song
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchSongItem(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: (Int) -> Unit = {},
) {
    val words = Html.fromHtml(song.songWords, Html.FROM_HTML_MODE_COMPACT).toString()

    Row(
        modifier = modifier.clickable { onClick(song.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Blue700, shape = CircleShape)
                .padding(8.dp),
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(R.drawable.ic_lib_music),
                contentDescription = null,
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {

            Text(
                modifier = Modifier,
                text = stringResource(R.string.song_index_number, song.songNumber),
                fontSize = 17.sp,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = words,
                fontFamily = FontRegular,
                maxLines = 2,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = Blue700
        )
    }
}