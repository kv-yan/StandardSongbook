package am.betel.settings.domain.model

import am.betel.songbook.common.presentation.ui.theme.Blue700
import am.betel.songbook.common.presentation.ui.theme.Pink40
import am.betel.songbook.common.presentation.ui.theme.Purple40
import am.betel.songbook.common.presentation.ui.theme.RoseRed
import am.betel.songbook.common.presentation.ui.theme.TestBue
import am.betel.songbook.common.presentation.ui.theme.TestCoral
import am.betel.songbook.common.presentation.ui.theme.TestGreen
import am.betel.songbook.common.presentation.ui.theme.gray_backgrount1
import am.betel.songbook.common.presentation.ui.theme.gray_backgrount2
import androidx.compose.ui.graphics.Color

enum class UISettings(
    val backgroundColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val unfocusedColor: Color,
    val primaryColor: Color,
) {
    LightBlue700(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Blue700
    ),

    LightRed(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = RoseRed
    ),

    LightPurple40(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Purple40
    ),

    LightPink40(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Pink40
    ),

    LightCoral(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = TestCoral
    ),

    DarkLightGray(
        backgroundColor = gray_backgrount1,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBue
    ),
    DarkDarkGrayBlue(
        backgroundColor = gray_backgrount2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBue
    ),
    DarkDarkGrayGreen(
        backgroundColor = gray_backgrount2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestGreen
    ),

}