package com.abkhrr.gazego.core.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.palette.graphics.Palette
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.designsystem.ext.hue
import com.abkhrr.gazego.core.designsystem.ext.themeSwatchSelection
import com.abkhrr.gazego.core.designsystem.ext.toColor
import com.abkhrr.gazego.core.designsystem.ext.withValues

@Composable
fun ContrastText(
    @StringRes textResource: Int,
    palette: Palette?,
    modifier: Modifier = Modifier,
    style: TextStyle = GazegoTheme.typography.medium.h6
) {
    val selection = palette?.themeSwatchSelection()
    val secondarySwatch = selection?.get(1)
    val neutralHue = secondarySwatch?.hsl?.hue()
    val color = secondarySwatch?.hsl?.withValues(hue = neutralHue)?.toColor() ?: Color.White

    Text(
        text = stringResource(
            id = textResource
        ),
        modifier = modifier,
        style = style,
        color = color
    )
}

@Composable
fun ContrastText(
    text: String,
    palette: Palette?,
    modifier: Modifier = Modifier,
    style: TextStyle = GazegoTheme.typography.medium.h6
) {
    val selection = palette?.themeSwatchSelection()
    val secondarySwatch = selection?.get(1)
    val neutralHue = secondarySwatch?.hsl?.hue()
    val color = secondarySwatch?.hsl?.withValues(hue = neutralHue)?.toColor() ?: Color.White

    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color
    )
}
