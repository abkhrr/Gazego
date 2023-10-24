package com.abkhrr.gazego.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.components.GazegoIconButton
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoWishlistButton(
    isWishlisted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = if (isWishlisted) R.drawable.ic_wishlish_selected else R.drawable.ic_wishlist_default

    GazegoIconButton(
        modifier = modifier
            .size(32.dp)
            .background(
                color = GazegoTheme.colors.surfaceColor,
                shape = GazegoTheme.shapes.smallMedium
            ),
        iconResourceId = icon,
        contentDescription = stringResource(id = R.string.wishlist),
        onClick = onClick,
        tint = GazegoTheme.colors.secondaryRed.let { color ->
            if (isWishlisted) color else GazegoTheme.colors.white
        }
    )
}

@Composable
fun GazegoBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = GazegoTheme.colors.white
) {
    GazegoIconButton(
        modifier = modifier
            .size(32.dp)
            .background(
                color = GazegoTheme.colors.surfaceColor,
                shape = GazegoTheme.shapes.smallMedium
            ),
        iconResourceId = R.drawable.ic_back,
        contentDescription = stringResource(id = R.string.back),
        onClick = onClick,
        tint = tint
    )
}
