package com.abkhrr.gazego.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.icon.GazegoIcon
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun RatingItem(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = GazegoTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = GazegoTheme.colors.primarySoft.copy(alpha = RatingItemContainerColorAlpha),
            contentColor = GazegoTheme.colors.secondaryOrange
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = GazegoTheme.spacing.small,
                vertical = GazegoTheme.spacing.extraSmall
            )
        ) {
            GazegoIcon(
                modifier = Modifier.size(RatingIconSize),
                iconResourceId = R.drawable.ic_stars,
                contentDescription = stringResource(id = R.string.rating),
                tint = GazegoTheme.colors.secondary
            )
            Spacer(modifier = Modifier.width(GazegoTheme.spacing.extraSmall))
            Text(
                text = if (rating == RatingNotRatedValue) {
                    stringResource(id = R.string.not_rated)
                } else {
                    String.format("%.1f", rating)
                },
                style = GazegoTheme.typography.semiBold.h6
            )
        }
    }
}

private val RatingIconSize = 16.dp
private const val RatingItemContainerColorAlpha = 0.72f
private const val RatingNotRatedValue = 0.0