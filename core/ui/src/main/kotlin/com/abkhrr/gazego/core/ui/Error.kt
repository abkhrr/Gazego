package com.abkhrr.gazego.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.abkhrr.gazego.core.designsystem.components.GazegoCenteredBox
import com.abkhrr.gazego.core.designsystem.components.GazegoOutlinedButton
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.ui.model.Message
import com.abkhrr.gazego.core.ui.model.asString

@Composable
fun GazegoError(
    errorMessage: Message,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = GazegoTheme.shapes.medium,
    containerColor: Color = GazegoTheme.colors.primarySoft,
    errorTextColor: Color = GazegoTheme.colors.secondaryRed,
    actionButtonColor: Color = GazegoTheme.colors.primaryBlue,
    errorTextStyle: TextStyle = GazegoTheme.typography.regular.h4,
    @StringRes actionButtonTextResourceId: Int = R.string.retry,
    shouldShowOfflineMode: Boolean = false,
    onOfflineModeClick: () -> Unit = {},
    offlineModeButtonColor: Color = GazegoTheme.colors.secondaryGreen,
    @StringRes offlineModeButtonTextResourceId: Int = R.string.offline_mode
) {
    Column(
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small)
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Column(
                modifier = Modifier.padding(GazegoTheme.spacing.extraMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small)
            ) {
                Text(
                    text = errorMessage.asString(),
                    style = errorTextStyle,
                    color = errorTextColor
                )
                GazegoOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    textResourceId = actionButtonTextResourceId,
                    onClick = onRetry,
                    containerColor = GazegoTheme.colors.primarySoft,
                    contentColor = actionButtonColor
                )
            }
        }
        if (shouldShowOfflineMode) {
            GazegoOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                textResourceId = offlineModeButtonTextResourceId,
                onClick = onOfflineModeClick,
                containerColor = GazegoTheme.colors.primaryDark,
                contentColor = offlineModeButtonColor
            )
        }
    }
}

@Composable
fun GazegoCenteredError(
    errorMessage: Message,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = GazegoTheme.shapes.medium,
    containerColor: Color = GazegoTheme.colors.primarySoft,
    errorTextColor: Color = GazegoTheme.colors.secondaryRed,
    actionButtonColor: Color = GazegoTheme.colors.primaryBlue,
    @StringRes actionButtonTextResourceId: Int = R.string.retry,
    shouldShowOfflineMode: Boolean = false,
    onOfflineModeClick: () -> Unit = {},
    offlineModeButtonColor: Color = GazegoTheme.colors.secondaryGreen,
    @StringRes offlineModeButtonTextResourceId: Int = R.string.offline_mode
) {
    GazegoCenteredBox(
        modifier = modifier
            .padding(horizontal = GazegoTheme.spacing.extraMedium)
            .fillMaxSize()
    ) {
        GazegoError(
            errorMessage = errorMessage,
            onRetry = onRetry,
            shape = shape,
            containerColor = containerColor,
            errorTextColor = errorTextColor,
            actionButtonColor = actionButtonColor,
            actionButtonTextResourceId = actionButtonTextResourceId,
            shouldShowOfflineMode = shouldShowOfflineMode,
            onOfflineModeClick = onOfflineModeClick,
            offlineModeButtonColor = offlineModeButtonColor,
            offlineModeButtonTextResourceId = offlineModeButtonTextResourceId
        )
    }
}