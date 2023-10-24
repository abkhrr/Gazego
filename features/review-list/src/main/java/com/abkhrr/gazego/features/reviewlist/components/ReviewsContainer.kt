package com.abkhrr.gazego.features.reviewlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.GazegoCenteredBox
import com.abkhrr.gazego.core.designsystem.components.GazegoCircularProgressIndicator
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.components.GazegoMessage
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.ui.GazegoError
import com.abkhrr.gazego.core.ui.RatingItem
import com.abkhrr.gazego.core.ui.model.Message
import com.abkhrr.gazego.core.ui.model.asMessage
import com.abkhrr.gazego.core.ui.utils.error
import com.abkhrr.gazego.core.ui.utils.isEmpty
import com.abkhrr.gazego.core.ui.utils.isError
import com.abkhrr.gazego.core.ui.utils.isFinished
import com.abkhrr.gazego.core.ui.utils.isLoading
import com.abkhrr.gazego.core.ui.utils.isNotEmpty
import com.abkhrr.gazego.features.reviewlist.R
import kotlinx.datetime.LocalDate

@Composable
fun ReviewsContainer(
    reviews: LazyPagingItems<Review>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = reviews.loadState.refresh.isLoading,
    loadingContent: @Composable LazyItemScope.() -> Unit = { GazegoCircularProgressIndicator() },
    emptyContent: @Composable LazyItemScope.() -> Unit = {
        GazegoMessage(
            modifier = Modifier.fillParentMaxSize(),
            messageResourceId = R.string.noReviewsAvailable,
            imageResourceId = R.drawable.reviews
        )
    },
    errorContent: @Composable LazyItemScope.(errorMessage: Message) -> Unit = { message ->
        GazegoError(
            modifier = Modifier.fillMaxSize(),
            errorMessage = message,
            onRetry = reviews::retry
        )
    }
) {
    SwipeRefresh(
        modifier = modifier,
        isRefreshing = isLoading,
        onRefresh = reviews::refresh
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small)
        ) {
            when {
                reviews.isNotEmpty() -> {
                    items(count = reviews.itemCount) { index ->
                        val review = reviews[index]
                        if (review == null) {
                            ReviewFeedItemPlaceHolder()
                        }
                        else {
                            ReviewFeed(item = review)
                        }
                    }
                    if (reviews.loadState.refresh.isError) {
                        item { errorContent(reviews.loadState.refresh.error.asMessage()) }
                    }
                }
                reviews.loadState.refresh.isLoading -> {
                    items(20) { ReviewFeedItemPlaceHolder() }
                }
                reviews.loadState.refresh.isFinished -> {
                    if (reviews.isEmpty()) {
                        item(content = emptyContent)
                    }
                }
                reviews.loadState.refresh.isError -> {
                    item {
                        GazegoCenteredBox(modifier = Modifier.fillParentMaxSize()) {
                            errorContent(reviews.loadState.refresh.error.asMessage())
                        }
                    }
                }
            }
            if (reviews.loadState.append.isLoading) {
                item { GazegoCenteredBox(modifier = Modifier.fillMaxWidth()) { loadingContent() } }
            }
            if (reviews.loadState.append.isError) {
                item { errorContent(reviews.loadState.append.error.asMessage()) }
            }
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
            .clip(shape)
            .padding(GazegoTheme.spacing.extraMedium),
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

@Composable
fun ReviewFeedItemPlaceHolder(
    modifier: Modifier = Modifier
) {
    ReviewFeedItem(
        modifier = modifier,
        reviewContent = emptyString(),
        createdAt = LocalDate(2023, 12, 20),
        username = emptyString(),
        avatarPath = emptyString(),
        rating = emptyDouble()
    )
}