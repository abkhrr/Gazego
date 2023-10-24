package com.abkhrr.gazego.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.annotation.DevicePreviews
import com.abkhrr.gazego.core.ui.GazegoCenteredError
import com.abkhrr.gazego.core.ui.model.asMessage
import com.abkhrr.gazego.features.home.components.CommonMoviesContainer
import com.abkhrr.gazego.features.home.components.UpcomingMovieContainer

@Composable
internal fun HomeRoute(
    onMovieClick: (Int) -> Unit,
    onSeeAllClick: (MovieCategory.Categories) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onMovieClick = onMovieClick,
        onSeeAllClick = onSeeAllClick,
        onRefresh = { viewModel.onEvent(HomeEvent.Refresh) },
        onRetry = { viewModel.onEvent(HomeEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(HomeEvent.ClearError) },
        modifier = modifier
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onMovieClick: (Int) -> Unit,
    onSeeAllClick: (MovieCategory.Categories) -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SwipeRefresh(
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        isRefreshing = uiState.isLoading,
        onRefresh = onRefresh
    ) {
        if (uiState.error != null) {
            GazegoCenteredError(
                errorMessage = uiState.error.asMessage(),
                onRetry = onRetry,
                shouldShowOfflineMode = uiState.isOfflineModeAvailable,
                onOfflineModeClick = onOfflineModeClick
            )
        } else {
            HomeContent(
                movies = uiState.movies,
                onMovieClick = onMovieClick,
                onSeeAllClick = onSeeAllClick
            )
        }
    }
}

@Composable
internal fun HomeContent(
    movies: Map<MovieCategory.Categories, List<Movie>>,
    onMovieClick: (Int) -> Unit,
    onSeeAllClick: (MovieCategory.Categories) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.extraMedium),
        contentPadding = PaddingValues(vertical = GazegoTheme.spacing.extraMedium)
    ) {
        item {
            UpcomingMovieContainer(
                movies = movies[MovieCategory.Categories.Upcoming].orEmpty(),
                onMovieClick = onMovieClick
            )
        }
        item {
            CommonMoviesContainer(
                movies = movies[MovieCategory.Categories.NowPlaying].orEmpty(),
                onMovieClick = onMovieClick,
                onSeeAllClick = { onSeeAllClick(MovieCategory.Categories.NowPlaying) },
                titleResourceId = R.string.nowPlaying,
            )
        }
        item {
            CommonMoviesContainer(
                movies = movies[MovieCategory.Categories.Popular].orEmpty().shuffled(),
                onMovieClick = onMovieClick,
                onSeeAllClick = { onSeeAllClick(MovieCategory.Categories.Popular) },
                titleResourceId = R.string.popular,
            )
        }
        item {
            CommonMoviesContainer(
                movies = movies[MovieCategory.Categories.TopRated].orEmpty().sortedByDescending { it.voteAverage },
                onMovieClick = onMovieClick,
                onSeeAllClick = { onSeeAllClick(MovieCategory.Categories.TopRated) },
                titleResourceId = R.string.topRated,
            )
        }
    }
}

@DevicePreviews
@Composable
fun HomePagePreview(
    @PreviewParameter(MovieResourcesPreview::class)
    uiState: HomeUiState
) {
    GazegoTheme {
        HomeScreen(
            uiState = homeUiState,
            onMovieClick = {},
            onRefresh = {},
            onRetry = {},
            onOfflineModeClick = {},
            onSeeAllClick = {},
            modifier = Modifier
        )
    }
}