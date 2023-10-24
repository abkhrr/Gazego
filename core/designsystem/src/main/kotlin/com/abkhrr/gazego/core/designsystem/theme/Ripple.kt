package com.abkhrr.gazego.core.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Immutable
internal object ThemedRipple : RippleTheme {
    @Composable
    override fun defaultColor() = RippleColor

    @Composable
    override fun rippleAlpha() = RippleAlpha
}

@Composable
internal fun rememberThemedRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = RippleColor
) = rememberRipple(bounded = bounded, radius = radius, color = color)

private val RippleColor: Color
    @Composable get() = GazegoTheme.colors.primaryBlue

private val RippleAlpha = RippleAlpha(
    draggedAlpha = 0.16f,
    focusedAlpha = 0.12f,
    hoveredAlpha = 0.08f,
    pressedAlpha = 0.12f
)