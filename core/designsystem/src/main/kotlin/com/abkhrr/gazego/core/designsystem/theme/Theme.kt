package com.abkhrr.gazego.core.designsystem.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun GazegoTheme(
    colorScheme: ColorScheme = DarkColorScheme,
    shapes: Shapes = Shapes,
    typography: Typography = Typography,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
    ) { ProvideThemeDependencies(content = content) }
}

@Composable
private fun ProvideThemeDependencies(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalThemedColors provides ThemedColors(),
        LocalThemedShapes provides ThemedShapes(),
        LocalThemedTypography provides ThemedTypography(),
        LocalThemedSpacing provides ThemedSpacing(),
        LocalIndication provides rememberThemedRipple(),
        LocalRippleTheme provides ThemedRipple
    ) {
        ProvideTextStyle(value = GazegoTheme.typography.regular.h4, content = content)
    }
}

object GazegoTheme {
    val colors: ThemedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedColors.current

    val shapes: ThemedShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedShapes.current

    val typography: ThemedTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedTypography.current

    val spacing: ThemedSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedSpacing.current
}
