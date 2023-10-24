package com.abkhrr.gazego.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.icon.GazegoIcon
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoOutlinedButton(
    @StringRes textResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = GazegoTheme.shapes.medium,
    containerColor: Color = GazegoTheme.colors.primarySoft,
    contentColor: Color = GazegoTheme.colors.primaryBlue,
    borderColor: Color = contentColor,
    textStyle: TextStyle = GazegoTheme.typography.medium.h5
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(brush = SolidColor(borderColor))
    ) {
        Text(text = stringResource(id = textResourceId), style = textStyle)
    }
}

@Composable
fun GazegoIconButton(
    @DrawableRes iconResourceId: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    GazegoIconButtonContent(
        modifier = modifier.padding(5.dp),
        onClick = onClick
    ) {
        GazegoIcon(
            modifier = modifier,
            iconResourceId = iconResourceId,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@Composable
fun GazegoIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    GazegoIconButtonContent(
        modifier = modifier,
        onClick = onClick,
    ) {
        GazegoIcon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ReusedModifierInstance")
@Composable
private fun GazegoIconButtonContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        IconButton(
            modifier = modifier,
            onClick = onClick,
            content = content
        )
    }
}