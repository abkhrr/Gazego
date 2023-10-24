package com.abkhrr.gazego.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.ui.utils.Constants

@Composable
fun GazegoHorizontalMovies(
    @StringRes titleResourceId: Int,
    movies: List<Movie>,
    onSeeAllClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    MoviesContainer(
        titleResourceId = titleResourceId,
        onSeeAllClick = onSeeAllClick,
        modifier = modifier.fillMaxWidth()
    ) {
        MoviesContentContainer(
            modifier = modifier,
            items = movies,
            itemContent = { movie -> HorizontalMovieItem(movie = movie, onClick = onMovieClick) },
            placeholderContent = { HorizontalMovieItemPlaceholder() }
        )
    }
}

@Composable
fun HorizontalMovieItem(
    movie: Movie,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    with(movie) {
        HorizontalFeedItem(
            title = title,
            posterPath = "${Constants.PosterImagePath.WIDTH_MEDIUM}${posterPath}",
            voteAverage = voteAverage,
            onClick = { onClick(id) },
            modifier = modifier
        )
    }
}

@Suppress("ReusedModifierInstance")
@Composable
fun VerticalMovieItem(
    movie: Movie,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    with(movie) {
        VerticalFeedItem(
            title = title,
            overview = overview,
            posterPath = posterPath,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            onClick = { onClick(id) },
            modifier = modifier
        )
    }
}

@Suppress("ReusedModifierInstance")
@Composable
fun VerticalMovieItem(
    movie: MovieDetails,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    with(movie) {
        VerticalFeedItem(
            title = title,
            overview = overview,
            posterPath = posterPath,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            onClick = { onClick(id) },
            modifier = modifier
        )
    }
}

@Composable
fun VerticalMovieItemPlaceholder(
    modifier: Modifier = Modifier
) {
    VerticalFeedItemPlaceholder(modifier = modifier)
}