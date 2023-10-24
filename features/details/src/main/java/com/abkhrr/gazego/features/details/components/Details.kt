package com.abkhrr.gazego.features.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyInt
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.components.GazegoOverlay
import com.abkhrr.gazego.core.designsystem.components.gazegoPlaceholder
import com.abkhrr.gazego.core.designsystem.components.shimmerEffect
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.ui.RatingItem
import com.abkhrr.gazego.core.ui.utils.Constants
import com.abkhrr.gazego.features.details.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsItem(
    id: Int,
    title: String,
    overview: String,
    posterPath: String?,
    runtime: String,
    genres: List<String>,
    reviews: List<Review>,
    video: Video?,
    releaseYear: String,
    voteAverage: Double,
    isOnWishlist: Boolean,
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    onSeeAllReviewsClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets.safeDrawing.only(
        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
    ),
    isPlaceholder: Boolean = false
) {
    val genreItems = when {
        genres.isEmpty() -> emptyList()
        genres.size <= 3 -> genres
        else -> genres.take(3)
    }

    val reviewsItem = when {
        reviews.isEmpty() -> emptyList()
        reviews.size <= 3 -> reviews
        else -> reviews.take(3)
    }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp)
        ) {
            if (isPlaceholder) {
                Box(
                    modifier = Modifier
                        .background(color = GazegoTheme.colors.primarySoft)
                        .fillMaxSize()
                )
            } else {
                GazegoImage(
                    modifier = Modifier.fillMaxSize(),
                    model = "${Constants.PosterImagePath.WIDTH_MEDIUM}${posterPath}",
                    contentDescription = title
                )
            }
            GazegoOverlay(color = GazegoTheme.colors.primaryDark, alpha = 0.2f)
        }
    }

    Scaffold(
        topBar = {
            if (isPlaceholder) {
                TopAppBarPlaceholder(
                    isWishlisted = isOnWishlist,
                    onBackButtonClick = onBackButtonClick,
                    onWishlistButtonClick = onWishlistButtonClick,
                )
            } else {
                TopAppBar(
                    title = title,
                    isOnWishlist = isOnWishlist,
                    onBackButtonClick = onBackButtonClick,
                    onWishlistButtonClick = onWishlistButtonClick,
                    windowInsets = windowInsets,
                )
            }
        },
        containerColor = Color.Transparent,
        contentWindowInsets = windowInsets,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
        ) {
            item {
                Box(modifier.height(420.dp))
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = GazegoTheme.spacing.largest),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.smallMedium)
                ) {
                    if (isPlaceholder) {
                        Text(
                            text = title,
                            color = GazegoTheme.colors.white,
                            style = GazegoTheme.typography.medium.h3
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = emptyString(),
                                modifier = Modifier.height(16.dp).fillMaxWidth(0.5f).shimmerEffect()
                            )
                            Text(
                                text = "•",
                                modifier = Modifier.height(16.dp).shimmerEffect()
                            )
                            Text(
                                text = emptyString(),
                                modifier = Modifier.height(16.dp).fillMaxWidth(0.5f).shimmerEffect()
                            )
                        }

                        RatingItem(
                            modifier = Modifier.gazegoPlaceholder(
                                color = GazegoTheme.colors.secondary
                            ),
                            rating = emptyDouble()
                        )
                    } else {
                        Text(
                            modifier = Modifier.padding(horizontal = GazegoTheme.spacing.small),
                            text = title,
                            style = GazegoTheme.typography.semiBold.h2,
                            color = GazegoTheme.colors.white,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = releaseYear,
                                color = GazegoTheme.colors.grey,
                                style = GazegoTheme.typography.medium.h5
                            )
                            Text(
                                text = "•",
                                color = GazegoTheme.colors.grey,
                                style = GazegoTheme.typography.medium.h5
                            )
                            Text(
                                text = runtime,
                                color = GazegoTheme.colors.grey,
                                style = GazegoTheme.typography.medium.h5
                            )
                        }

                        HorizontalGenres(
                            modifier = Modifier.fillMaxWidth(),
                            genres = genreItems,
                        )

                        RatingItem(rating = voteAverage)
                    }
                }
            }
            item {
                if (isPlaceholder) OverviewPlaceholder() else Overview(overview = overview)
            }
            item {
                TrailerPlayer(
                    modifier = Modifier.fillMaxWidth(),
                    video = video
                )
            }
            item {
                Reviews(reviews = reviewsItem, onSeeAllReviewsClick = onSeeAllReviewsClick, movieId = id)
            }
            item {
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
    }
}

@Composable
internal fun DetailsItemPlaceholder(
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DetailsItem(
        modifier = modifier,
        title = emptyString(),
        overview = emptyString(),
        posterPath = null,
        runtime = emptyString(),
        genres = emptyList(),
        reviews = emptyList(),
        voteAverage = emptyDouble(),
        isOnWishlist = false,
        onBackButtonClick = onBackButtonClick,
        onWishlistButtonClick = onWishlistButtonClick,
        onSeeAllReviewsClick = {},
        isPlaceholder = true,
        releaseYear = "2023",
        id = emptyInt(),
        video = null
    )
}