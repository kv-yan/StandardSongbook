package am.betel.songbook.list.presentation.components


import am.betel.settings.domain.model.UISettings
import am.betel.songbook.R
import am.betel.songbook.common.presentation.ui.theme.FontBold
import am.betel.songbook.common.presentation.ui.theme.FontRegular
import am.betel.songbook.common.presentation.ui.theme.Shape16
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ListRageItem(
    modifier: Modifier = Modifier,
    uiSettings: UISettings,
    intRange: IntRange,
    initialExpanded: Boolean = false,
    navigateToDetails: (Int) -> Unit = {},
) {
    var isExpanded by rememberSaveable { mutableStateOf(initialExpanded) }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_music),
                contentDescription = null,
                tint = uiSettings.primaryColor
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                text = stringResource(R.string.items_range, intRange.first, intRange.last),
                style = TextStyle(
                    fontFamily = FontRegular,
                    fontSize = 16.sp,
                    color = uiSettings.primaryTextColor
                )
            )

            IconButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = uiSettings.primaryTextColor
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpanded
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = uiSettings.primaryTextColor,
                        shape = Shape16
                    )
                    .padding(4.dp)
                    .heightIn(
                        min = 0.dp,
                        max = 2000.dp
                    ),
                columns = GridCells.Fixed(4),
            ) {
                items(intRange.toList(), key = { it }) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { navigateToDetails(it) },
                        shape = RoundedCornerShape(0),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = uiSettings.backgroundColor,
                                contentColor = uiSettings.primaryTextColor
                            )
                    ) {
                        Text(
                            text = it.toString(),
                            style = TextStyle(
                                fontFamily = FontBold,
                                fontSize = 15.sp,
                                color = uiSettings.primaryTextColor
                            )
                        )
                    }
                }
            }
        }
    }
}