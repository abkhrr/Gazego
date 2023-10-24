package com.abkhrr.gazego.features.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.ui.RatingItem
import com.abkhrr.gazego.features.details.R
import kotlinx.datetime.LocalDate

@Composable
internal fun Reviews(
    reviews: List<Review>,
    movieId: Int,
    onSeeAllReviewsClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val reviewHeight = with(LocalDensity.current) { 150.dp.toPx() }
    val itemSpace = with(LocalDensity.current) { 8.dp.toPx() }

    val space = 16 * reviews.size
    val itemHeight = reviewHeight * (reviews.size + space)
    val lazyColumnHeight = itemHeight.dp

    Column(
        modifier = modifier
            .padding(horizontal = GazegoTheme.spacing.extraMedium)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = GazegoTheme.spacing.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.reviewTitle),
                style = GazegoTheme.typography.semiBold.h5,
                color = GazegoTheme.colors.white
            )
            TextButton(onClick = { onSeeAllReviewsClick(movieId) }) {
                Text(
                    text = stringResource(id = com.abkhrr.gazego.core.ui.R.string.see_all),
                    style = GazegoTheme.typography.medium.h5,
                    color = GazegoTheme.colors.secondary
                )
            }
        }

        LazyColumn(
            modifier = Modifier.height(lazyColumnHeight),
            verticalArrangement = Arrangement.spacedBy(itemSpace.dp)
        ) {
            items(items = reviews, itemContent = { review -> ReviewFeed(review) })
        }
    }
}

@Composable
fun ReviewFeed(
    item: Review,
    modifier: Modifier = Modifier
){
    with(item) {
        ReviewFeedItem(
            reviewContent = content,
            username = authorDetails.username,
            avatarPath = authorDetails.avatarPath,
            rating = authorDetails.rating,
            createdAt = createdAt,
            modifier = modifier
        )
    }
}

@Composable
fun ReviewFeedItem(
    reviewContent: String,
    username: String,
    createdAt: LocalDate,
    modifier: Modifier = Modifier,
    avatarPath: String? = emptyString(),
    rating: Double? = emptyDouble(),
    shape: Shape = GazegoTheme.shapes.small
) {
    val avatar = if (avatarPath.orEmpty().isEmpty()) {
        "https://image.tmdb.org/t/p/w200/xy44UvpbTgzs9kWmp4C3fEaCl5h.png"
    } else {
        "https://image.tmdb.org/t/p/w200$avatarPath"
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape),
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.extraMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
        ) {
            GazegoImage(
                modifier = Modifier.size(48.dp).clip(CircleShape),
                model = avatar,
                contentDescription = "$username image"
            )
            Text(
                modifier = Modifier.weight(1f),
                text = username,
                style = GazegoTheme.typography.medium.h4,
                color = GazegoTheme.colors.white
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.smallMedium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
            ) {
                RatingItem(
                    rating = rating ?: emptyDouble(),
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = createdAt.toString(),
                    style = GazegoTheme.typography.medium.h6,
                    color = GazegoTheme.colors.whiteGrey
                )
            }

            Text(
                text = reviewContent,
                style = GazegoTheme.typography.regular.h5,
                color = GazegoTheme.colors.white,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}