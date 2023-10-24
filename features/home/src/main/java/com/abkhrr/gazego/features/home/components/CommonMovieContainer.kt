package com.abkhrr.gazego.features.home.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.GazegoHorizontalMovies

@Composable
fun CommonMoviesContainer(
    movies: List<Movie>,
    @StringRes titleResourceId: Int,
    onSeeAllClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    GazegoHorizontalMovies(
        movies = movies,
        titleResourceId = titleResourceId,
        onSeeAllClick = onSeeAllClick,
        onMovieClick = onMovieClick,
        modifier = modifier
    )
}