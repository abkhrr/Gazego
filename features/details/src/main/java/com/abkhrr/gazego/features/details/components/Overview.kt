package com.abkhrr.gazego.features.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.gazegoPlaceholder
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.features.details.R

@Composable
internal fun Overview(
    overview: String,
    modifier: Modifier = Modifier,
    isPlaceholder: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(horizontal = GazegoTheme.spacing.extraMedium)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small)
    ) {
        Text(
            text = stringResource(id = R.string.overviewTitle),
            style = GazegoTheme.typography.semiBold.h4,
            color = GazegoTheme.colors.white
        )
        Text(
            modifier = if (isPlaceholder) {
                Modifier
                    .fillMaxWidth()
                    .gazegoPlaceholder(color = GazegoTheme.colors.grey)
            } else {
                Modifier
            },
            text = overview.ifEmpty { stringResource(id = R.string.emptyOverview) },
            style = GazegoTheme.typography.regular.h5,
            color = GazegoTheme.colors.whiteGrey
        )
    }
}

@Composable
internal fun OverviewPlaceholder(modifier: Modifier = Modifier) {
    Overview(modifier = modifier, overview = emptyString(), isPlaceholder = true)
}