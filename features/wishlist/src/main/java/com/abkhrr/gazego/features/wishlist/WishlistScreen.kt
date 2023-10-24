package com.abkhrr.gazego.features.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abkhrr.gazego.core.designsystem.components.GazegoMessage
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.ui.GazegoCenteredError
import com.abkhrr.gazego.core.ui.VerticalMovieItem
import com.abkhrr.gazego.core.ui.VerticalMovieItemPlaceholder
import com.abkhrr.gazego.core.ui.model.asMessage

@Composable
internal fun WishlistRoute(
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistScreen(
        uiState = uiState,
        onRefreshMovies = { viewModel.onEvent(WishlistEvent.RefreshMovies) },
        onMovieClick = onMovieClick,
        onRetry = { viewModel.onEvent(WishlistEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(WishlistEvent.ClearError) },
        modifier = modifier
    )
}

@Composable
private fun WishlistScreen(
    uiState: WishlistUiState,
    onRefreshMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {
        if (uiState.error != null) {
            GazegoCenteredError(
                errorMessage = uiState.error.asMessage(),
                onRetry = onRetry,
                shouldShowOfflineMode = uiState.isOfflineModeAvailable,
                onOfflineModeClick = onOfflineModeClick
            )
        } else {
            MoviesContainer(
                movies = uiState.movies,
                isLoading = uiState.isMoviesLoading,
                onRefresh = onRefreshMovies,
                onClick = onMovieClick
            )
        }
    }
}

@Composable
private fun MoviesContainer(
    movies: List<MovieDetails>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable LazyItemScope.() -> Unit = {
        GazegoMessage(
            modifier = Modifier.fillParentMaxSize(),
            messageResourceId = R.string.noWishlist,
            imageResourceId = R.drawable.no_wishlist
        )
    }
) {
    SwipeRefresh(
        modifier = modifier,
        isRefreshing = isLoading,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(GazegoTheme.colors.primaryDark),
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium),
            contentPadding = PaddingValues(GazegoTheme.spacing.extraMedium)
        ) {
            when {
                movies.isNotEmpty() -> {
                    items(movies) { movie ->
                        VerticalMovieItem(
                            movie = movie,
                            onClick = onClick,
                        )
                    }
                }
                isLoading -> {
                    items(20) { VerticalMovieItemPlaceholder() }
                }
                else -> item(content = emptyContent)
            }
        }
    }
}