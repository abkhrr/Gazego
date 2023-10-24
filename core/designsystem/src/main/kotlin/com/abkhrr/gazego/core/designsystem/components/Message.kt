package com.abkhrr.gazego.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoMessage(
    @StringRes messageResourceId: Int,
    @DrawableRes imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    GazegoCenteredBox(modifier = modifier.padding(horizontal = GazegoTheme.spacing.largest)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(id = messageResourceId)
            )
            Text(
                text = stringResource(id = messageResourceId),
                style = GazegoTheme.typography.medium.h3,
                color = GazegoTheme.colors.white,
                textAlign = TextAlign.Center
            )
        }
    }
}