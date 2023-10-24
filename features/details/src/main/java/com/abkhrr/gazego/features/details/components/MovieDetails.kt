package com.abkhrr.gazego.features.details.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.ui.mapper.asGenreName
import com.abkhrr.gazego.features.details.R

@Suppress("ReusedModifierInstance")
@Composable
internal fun MovieDetailsItem(
    movieDetails: MovieDetails,
    reviews: List<Review>,
    modifier: Modifier = Modifier,
    video: Video? = null,
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    onSeeAllReviewsClick: (Int) -> Unit,
) {
    with(movieDetails) {
        val genre = genres?.map { genre -> genre.asGenreName() }
        val hours = runtime / 60
        val minutes = runtime % 60

        // Create a formatted string for runtime
        val runtimeText = stringResource(R.string.details_runtime_text, "${hours}h", "${minutes}m")
        val releaseYear = releaseDate?.year

        DetailsItem(
            id = id,
            modifier = modifier,
            title = title,
            overview = overview,
            posterPath = posterPath,
            runtime = if (runtime == 0) stringResource(id = R.string.emptyRuntime) else runtimeText,
            genres = genre.orEmpty(),
            reviews = reviews,
            voteAverage = voteAverage,
            isOnWishlist = isOnWishlist,
            onBackButtonClick = onBackButtonClick,
            onWishlistButtonClick = onWishlistButtonClick,
            onSeeAllReviewsClick = onSeeAllReviewsClick,
            releaseYear = "$releaseYear",
            video = video
        )
    }
}

@Composable
internal fun MovieDetailsItemPlaceholder(
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DetailsItemPlaceholder(
        modifier = modifier,
        onBackButtonClick = onBackButtonClick,
        onWishlistButtonClick = onWishlistButtonClick
    )
}