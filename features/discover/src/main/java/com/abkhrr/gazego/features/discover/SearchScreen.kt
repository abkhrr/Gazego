package com.abkhrr.gazego.features.discover

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.abkhrr.gazego.core.designsystem.components.GazegoIconButton
import com.abkhrr.gazego.core.designsystem.components.GazegoTextField
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.ui.GazegoCenteredError
import com.abkhrr.gazego.core.ui.MoviesContainer
import com.abkhrr.gazego.core.ui.mapper.asMovie
import com.abkhrr.gazego.core.ui.model.asMessage
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun SearchRoute(
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchMovies = uiState.searchMovies.collectAsLazyPagingItems()
    SearchScreen(
        modifier = modifier,
        uiState = uiState,
        searchMovies = searchMovies,
        onRefresh = { viewModel.onEvent(SearchEvent.Refresh) },
        onQueryChange = { viewModel.onEvent(SearchEvent.ChangeQuery(it)) },
        onMovieClick = onMovieClick,
        onRetry = { viewModel.onEvent(SearchEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(SearchEvent.ClearError) }
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchMovies: LazyPagingItems<Movie>,
    onRefresh: () -> Unit,
    onQueryChange: (String) -> Unit,
    onMovieClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        SearchTextField(
            query = uiState.query,
            onQueryChange = onQueryChange
        )
        AnimatedContent(targetState = uiState.isSearching, label = "") { isSearching ->
            if (isSearching) {
                MoviesContainer(
                    modifier = Modifier.fillMaxWidth(),
                    movies = searchMovies,
                    onClick = onMovieClick
                )
            } else {
                SwipeRefresh(
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
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current
) {
    GazegoTextField(
        modifier = modifier
            .padding(
                start = GazegoTheme.spacing.extraMedium,
                top = GazegoTheme.spacing.small,
                end = GazegoTheme.spacing.extraMedium,
                bottom = GazegoTheme.spacing.extraMedium
            )
            .fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        placeholderResourceId = R.string.searchPlaceholder,
        iconResourceId = R.drawable.ic_search,
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn() + scaleIn(),
                exit = scaleOut() + fadeOut()
            ) {
                GazegoIconButton(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(id = R.string.clearSearch),
                    onClick = { onQueryChange("") }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
    )
}