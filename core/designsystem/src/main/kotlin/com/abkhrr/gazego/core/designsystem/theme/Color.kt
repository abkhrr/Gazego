package com.abkhrr.gazego.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val BlueAccent = Color(0xFF12CDD9)
private val Green = Color(0xFF22B07D)
private val Orange = Color(0xFFFF8700)
private val Red = Color(0xFFFB4141)

private val PrimaryColor = Color(0XFF19181E)
private val PrimarySoftColor = Color(0xFF252836)
private val SecondaryColor = Color(0XFFFECB6F)
private val SurfaceColor = Color(0XFF36353D)
private val Black = Color(0xFF171725)
private val Grey = Color(0xFF92929D)
private val DarkGrey = Color(0xFF696974)
private val White = Color(0xFFFFFFFF)
private val WhiteGrey = Color(0xFFEBEBEF)
private val LineDark = Color(0xFFEAEAEA)

internal val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = PrimaryColor,
    surface = SurfaceColor
)

@Immutable
data class ThemedColors(
    val default: Color = Color.Unspecified,
    val primaryDark: Color = PrimaryColor,
    val primarySoft: Color = PrimarySoftColor,
    val surfaceColor: Color = SurfaceColor,
    val primaryBlue: Color = BlueAccent,
    val secondary: Color = SecondaryColor,
    val secondaryGreen: Color = Green,
    val secondaryOrange: Color = Orange,
    val secondaryRed: Color = Red,
    val white: Color = White,
    val whiteGrey: Color = WhiteGrey,
    val black: Color = Black,
    val grey: Color = Grey,
    val darkGrey: Color = DarkGrey,
    val lineDark: Color = LineDark
)

internal val LocalThemedColors = staticCompositionLocalOf { ThemedColors() }