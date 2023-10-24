package com.abkhrr.gazego.core.designsystem.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoSnackbarHost(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    shape: Shape = GazegoTheme.shapes.small,
    containerColor: Color = GazegoTheme.colors.primarySoft,
    contentColor: Color = GazegoTheme.colors.whiteGrey,
    actionColor: Color = GazegoTheme.colors.primaryBlue
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    ) { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            actionColor = actionColor
        )
    }
}

val LocalSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error(LocalSnackbarHostStateErrorMessage)
}

private const val LocalSnackbarHostStateErrorMessage = "No SnackbarHostState provided."