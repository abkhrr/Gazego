package com.abkhrr.gazego.core.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GazegoTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = GazegoTheme.colors.primaryDark,
        titleContentColor = GazegoTheme.colors.white,
        navigationIconContentColor = GazegoTheme.colors.white
    )
) {
    CenterAlignedTopAppBar(
        modifier = modifier.padding(horizontal = GazegoTheme.spacing.extraMedium),
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GazegoTopAppBar(
    @StringRes titleResourceId: Int,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    GazegoTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = titleResourceId),
                style = GazegoTheme.typography.semiBold.h4,
                color = GazegoTheme.colors.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets
    )
}