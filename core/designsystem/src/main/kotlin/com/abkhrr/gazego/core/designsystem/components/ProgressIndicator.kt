package com.abkhrr.gazego.core.designsystem.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = GazegoTheme.colors.primaryBlue,
    strokeWidth: Dp = StrokeWidth
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth
    )
}

private val StrokeWidth = 2.dp