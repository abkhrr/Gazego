package com.abkhrr.gazego.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.components.GazegoImagePlaceholder
import com.abkhrr.gazego.core.designsystem.components.gazegoPlaceholder
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
internal fun HorizontalFeedItem(
    title: String,
    posterPath: String?,
    voteAverage: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = GazegoTheme.colors.primarySoft,
    shape: Shape = GazegoTheme.shapes.smallMedium,
    isPlaceholder: Boolean = false
) {
    Card(
        modifier = modifier
            .width(HorizontalFeedItemWidth)
            .clip(shape)
            .then(if (isPlaceholder) Modifier else Modifier.clickable(onClick = onClick)),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = shape
    ) {
        Box(modifier = Modifier.height(HorizontalFeedItemPosterHeight)) {
            if (isPlaceholder) {
                GazegoImagePlaceholder()
            } else {
                GazegoImage(
                    modifier = Modifier.fillMaxSize(),
                    model = posterPath,
                    contentDescription = title
                )
            }
            RatingItem(
                rating = voteAverage,
                modifier = Modifier
                    .padding(
                        top = GazegoTheme.spacing.small,
                        end = GazegoTheme.spacing.small
                    )
                    .align(Alignment.TopEnd)
                    .then(
                        if (isPlaceholder) {
                            Modifier.gazegoPlaceholder(color = GazegoTheme.colors.secondary)
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}

private val HorizontalFeedItemWidth = 135.dp
private val HorizontalFeedItemPosterHeight = 190.dp