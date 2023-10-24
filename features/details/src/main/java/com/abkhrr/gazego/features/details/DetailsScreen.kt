package com.abkhrr.gazego.features.details

import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abkhrr.gazego.core.common.ext.findComponentActivity
import com.abkhrr.gazego.core.designsystem.components.GazegoTopAppBar
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.GazegoBackButton
import com.abkhrr.gazego.core.ui.GazegoCenteredError
import com.abkhrr.gazego.core.ui.SnackbarMessageHandler
import com.abkhrr.gazego.core.ui.model.Message
import com.abkhrr.gazego.core.ui.model.asMessage
import com.abkhrr.gazego.features.details.components.MovieDetailsItem
import com.abkhrr.gazego.features.details.components.MovieDetailsItemPlaceholder

@Composable
internal fun DetailsRoute(
    onBackButtonClick: () -> Unit,
    onSeeAllReviewsClick: (Int) -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context.findComponentActivity()
    val darkTheme = isSystemInDarkTheme()

    DetailsScreen(
        uiState = uiState,
        onShowMessage = onShowMessage,
        onRefresh = { viewModel.onEvent(DetailsEvent.Refresh) },
        onBackButtonClick = onBackButtonClick,
        onSeeAllReviewsClick = onSeeAllReviewsClick,
        onWishlistMovieClick = { viewModel.onEvent(DetailsEvent.Wishlist) },
        onRetry = { viewModel.onEvent(DetailsEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(DetailsEvent.ClearError) },
        onUserMessageDismiss = { viewModel.onEvent(DetailsEvent.ClearUserMessage) },
        modifier = modifier
    )

    DisposableEffect(isSystemInDarkTheme()) {
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            ){ darkTheme },
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb(),
            ){ darkTheme },
        )
        onDispose {
            activity.enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    Color.Transparent.toArgb(),
                    Color.Transparent.toArgb(),
                ){ darkTheme },
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = darkScrim,
                    darkScrim = darkScrim,
                ) { darkTheme },
            )
        }
    }
}

@Composable
private fun DetailsScreen(
    uiState: DetailsUIState,
    onShowMessage: (String) -> Unit,
    onRefresh: () -> Unit,
    onBackButtonClick: () -> Unit,
    onSeeAllReviewsClick: (Int) -> Unit,
    onWishlistMovieClick: () -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    onUserMessageDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    SnackbarMessageHandler(
        messages = uiState.message,
        onShowMessage = onShowMessage,
        onDismiss = onUserMessageDismiss
    )
    SwipeRefresh(
        modifier = modifier,
        isRefreshing = uiState.isLoading,
        onRefresh = onRefresh,
        indicatorPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        if (uiState.error != null) {
            ErrorContent(
                errorMessage = uiState.error.asMessage(),
                isOfflineModeAvailable = uiState.isOfflineModeAvailable,
                onBackButtonClick = onBackButtonClick,
                onRetry = onRetry,
                onOfflineModeClick = onOfflineModeClick
            )
        } else {
            DetailsContent(
                uiState = uiState,
                onBackButtonClick = onBackButtonClick,
                onWishlistMovieClick = onWishlistMovieClick,
                onSeeAllReviewsClick = onSeeAllReviewsClick,
            )
        }
    }
}

@Composable
private fun DetailsContent(
    uiState: DetailsUIState,
    onBackButtonClick: () -> Unit,
    onWishlistMovieClick: () -> Unit,
    onSeeAllReviewsClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val videos = uiState.videos.orEmpty()
    val trailerVideo = videos.find { it.type == "Trailer" && it.site == "YouTube" }

    Surface(
        modifier = modifier,
        color = GazegoTheme.colors.primaryDark
    ) {
        when (uiState.category) {
            is MovieCategory.Details.Movie -> {
                if (uiState.movie == null) {
                    MovieDetailsItemPlaceholder(
                        onBackButtonClick = onBackButtonClick,
                        onWishlistButtonClick = onWishlistMovieClick
                    )
                } else {
                    MovieDetailsItem(
                        movieDetails = uiState.movie,
                        onBackButtonClick = onBackButtonClick,
                        onWishlistButtonClick = onWishlistMovieClick,
                        onSeeAllReviewsClick = onSeeAllReviewsClick,
                        reviews = uiState.review.orEmpty(),
                        video = trailerVideo
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun ErrorContent(
    errorMessage: Message,
    isOfflineModeAvailable: Boolean,
    onBackButtonClick: () -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GazegoTopAppBar(
                title = {},
                navigationIcon = { GazegoBackButton(onClick = onBackButtonClick) }
            )
        }
    ) { innerPadding ->
        GazegoCenteredError(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            errorMessage = errorMessage,
            onRetry = onRetry,
            shouldShowOfflineMode = isOfflineModeAvailable,
            onOfflineModeClick = onOfflineModeClick
        )
    }
}

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)