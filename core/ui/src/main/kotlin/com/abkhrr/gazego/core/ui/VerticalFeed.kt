package com.abkhrr.gazego.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.components.GazegoImagePlaceholder
import com.abkhrr.gazego.core.designsystem.components.gazegoPlaceholder
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Genre
import com.abkhrr.gazego.core.ui.utils.Constants
import kotlinx.datetime.LocalDate

@Composable
internal fun VerticalFeedItem(
    title: String,
    posterPath: String?,
    voteAverage: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    releaseDate: LocalDate?,
    overview: String,
    shape: Shape = GazegoTheme.shapes.smallMedium,
    isPlaceholder: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(VerticalFeedItemHeight)
            .clip(shape)
            .then(if (isPlaceholder) Modifier else Modifier.clickable(onClick = onClick)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
    ) {
        Box(
            modifier = Modifier
                .width(VerticalFeedItemPosterWidth)
                .fillMaxHeight()
                .clip(shape)
        ) {
            if (isPlaceholder) {
                GazegoImagePlaceholder()
            } else {
                GazegoImage(
                    modifier = Modifier.fillMaxSize(),
                    model = "${Constants.PosterImagePath.WIDTH_MEDIUM}${posterPath}",
                    contentDescription = title
                )
            }
            RatingItem(
                rating = voteAverage,
                modifier = Modifier
                    .padding(
                        top = GazegoTheme.spacing.small,
                        start = GazegoTheme.spacing.small
                    )
                    .align(Alignment.TopStart)
                    .then(
                        if (isPlaceholder) {
                            Modifier.gazegoPlaceholder(color = GazegoTheme.colors.secondaryOrange)
                        } else {
                            Modifier
                        }
                    )
            )
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.smallMedium)) {
                Text(
                    modifier = if (isPlaceholder) {
                        Modifier
                            .fillMaxWidth()
                            .gazegoPlaceholder(color = GazegoTheme.colors.whiteGrey)
                    } else {
                        Modifier
                    },
                    text = title,
                    style = GazegoTheme.typography.semiBold.h4,
                    color = GazegoTheme.colors.white,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = if (isPlaceholder) {
                        Modifier
                            .fillMaxWidth()
                            .gazegoPlaceholder(color = GazegoTheme.colors.grey)
                    } else {
                        Modifier
                    },
                    text = overview.ifEmpty { stringResource(id = R.string.no_overview) },
                    style = GazegoTheme.typography.medium.h5,
                    color = GazegoTheme.colors.grey,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                modifier = if (isPlaceholder) {
                    Modifier
                        .fillMaxWidth()
                        .gazegoPlaceholder(color = GazegoTheme.colors.grey)
                } else {
                    Modifier
                },
                text = "Released on: $releaseDate",
                style = GazegoTheme.typography.medium.h6,
                color = GazegoTheme.colors.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
internal fun VerticalFeedItemPlaceholder(modifier: Modifier = Modifier) {
    VerticalFeedItem(
        modifier = modifier,
        title = emptyString(),
        posterPath = emptyString(),
        voteAverage = emptyDouble(),
        overview = emptyString(),
        onClick = {},
        isPlaceholder = true,
        releaseDate = null
    )
}

private val VerticalFeedItemHeight = 150.dp
private val VerticalFeedItemPosterWidth = 112.dp