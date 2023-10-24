package com.abkhrr.features.movieList

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.abkhrr.gazego.core.designsystem.components.GazegoTopAppBar
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.GazegoBackButton
import com.abkhrr.gazego.core.ui.MoviesContainer
import com.abkhrr.gazego.features.movieList.R

@Composable
internal fun MovieListRoute(
    onBackButtonClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movies = uiState.movies.collectAsLazyPagingItems()
    ListScreen(
        modifier = modifier,
        movies = movies,
        onBackButtonClick = onBackButtonClick,
        onMovieClick = onMovieClick,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ListScreen(
    movies: LazyPagingItems<Movie>,
    onBackButtonClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            GazegoTopAppBar(
                titleResourceId = R.string.movie_list,
                navigationIcon = { GazegoBackButton(onClick = onBackButtonClick) }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Top
        )
    ) { innerPadding ->
        MoviesContainer(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            movies = movies,
            onClick = onMovieClick
        )
    }
}