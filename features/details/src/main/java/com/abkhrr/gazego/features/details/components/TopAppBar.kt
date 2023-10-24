package com.abkhrr.gazego.features.details.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.GazegoTopAppBar
import com.abkhrr.gazego.core.designsystem.components.gazegoPlaceholder
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.ui.GazegoBackButton
import com.abkhrr.gazego.core.ui.GazegoWishlistButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBar(
    title: String,
    isOnWishlist: Boolean,
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    titleColor: Color = GazegoTheme.colors.white,
    isPlaceholder: Boolean = false
) {
    GazegoTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier
                    .padding(horizontal = GazegoTheme.spacing.small)
                    .then(
                        if (isPlaceholder) {
                            Modifier
                                .fillMaxWidth()
                                .gazegoPlaceholder(color = titleColor)
                        } else {
                            Modifier
                        }
                    ),
                text = title,
                style = GazegoTheme.typography.semiBold.h4,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = { GazegoBackButton(onClick = onBackButtonClick) },
        actions = {
            GazegoWishlistButton(isWishlisted = isOnWishlist, onClick = onWishlistButtonClick)
        },
        windowInsets = windowInsets,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = GazegoTheme.colors.white,
            navigationIconContentColor = GazegoTheme.colors.white
        )
    )
}

@Composable
internal fun TopAppBarPlaceholder(
    isWishlisted: Boolean,
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = emptyString(),
        isOnWishlist = isWishlisted,
        onBackButtonClick = onBackButtonClick,
        onWishlistButtonClick = onWishlistButtonClick,
        isPlaceholder = true
    )
}
