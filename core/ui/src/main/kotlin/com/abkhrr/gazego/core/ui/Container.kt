package com.abkhrr.gazego.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Movie
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.abkhrr.gazego.core.designsystem.components.GazegoCenteredBox
import com.abkhrr.gazego.core.designsystem.components.GazegoCircularProgressIndicator
import com.abkhrr.gazego.core.designsystem.components.GazegoMessage
import com.abkhrr.gazego.core.designsystem.components.SwipeRefresh
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.model.Message
import com.abkhrr.gazego.core.ui.model.asMessage
import com.abkhrr.gazego.core.ui.utils.error
import com.abkhrr.gazego.core.ui.utils.isEmpty
import com.abkhrr.gazego.core.ui.utils.isError
import com.abkhrr.gazego.core.ui.utils.isFinished
import com.abkhrr.gazego.core.ui.utils.isLoading
import com.abkhrr.gazego.core.ui.utils.isNotEmpty

@Composable
fun MoviesContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(GazegoTheme.spacing.extraSmall))
        content()
    }
}

@Composable
fun MoviesContainer(
    @StringRes titleResourceId: Int,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        ContainerTitleWithButton(titleResourceId, onSeeAllClick)
        Spacer(modifier = Modifier.height(GazegoTheme.spacing.extraSmall))
        content()
    }
}

@Composable
fun MoviesContainer(
    movies: LazyPagingItems<Movie>,
    loadingContent: @Composable LazyItemScope.() -> Unit = { GazegoCircularProgressIndicator() },
    onClick: (Int) -> Unit,
    isLoading: Boolean = movies.loadState.refresh.isLoading,
    emptyContent: @Composable LazyItemScope.() -> Unit = {
        GazegoMessage(
            modifier = Modifier.fillParentMaxSize(),
            messageResourceId = R.string.movie_empty,
            imageResourceId = R.drawable.no_search_results
        )
    },
    errorContent: @Composable LazyItemScope.(errorMessage: Message) -> Unit = { message ->
        GazegoError(
            modifier = Modifier.fillMaxSize(),
            errorMessage = message,
            onRetry = movies::retry
        )
    },
    modifier: Modifier
) {
    SwipeRefresh(
        modifier = modifier,
        isRefreshing = isLoading,
        onRefresh = movies::refresh
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.medium)
        ) {
            when {
                movies.isNotEmpty() -> {
                    items(count = movies.itemCount) { index ->
                        val movie = movies[index]
                        if (movie == null) {
                            VerticalMovieItemPlaceholder()
                        } else {
                            VerticalMovieItem(movie = movie, onClick = onClick)
                        }
                    }
                    if (movies.loadState.refresh.isError) {
                        item { errorContent(movies.loadState.refresh.error.asMessage()) }
                    }
                }
                movies.loadState.refresh.isLoading -> {
                    items(20) { VerticalMovieItemPlaceholder() }
                }
                movies.loadState.refresh.isFinished -> {
                    if (movies.isEmpty()) {
                        item(content = emptyContent)
                    }
                }
                movies.loadState.refresh.isError -> {
                    item {
                        GazegoCenteredBox(modifier = Modifier.fillParentMaxSize()) {
                            errorContent(movies.loadState.refresh.error.asMessage())
                        }
                    }
                }
            }
            if (movies.loadState.append.isLoading) {
                item { GazegoCenteredBox(modifier = Modifier.fillMaxWidth()) { loadingContent() } }
            }
            if (movies.loadState.append.isError) {
                item { errorContent(movies.loadState.append.error.asMessage()) }
            }
        }
    }
}

@Composable
private fun ContainerTitleWithButton(
    @StringRes titleResourceId: Int,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = GazegoTheme.spacing.small)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = titleResourceId),
            style = GazegoTheme.typography.semiBold.h4,
            color = GazegoTheme.colors.white,
            modifier = modifier.padding(start = 8.dp)
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = GazegoTheme.typography.medium.h5,
                color = GazegoTheme.colors.secondary
            )
        }
    }
}

@Composable
fun MoviesContentContainer(
    items: List<Movie>,
    itemContent: @Composable LazyItemScope.(Movie) -> Unit,
    placeholderContent: @Composable LazyItemScope.(Int) -> Unit,
    modifier: Modifier = Modifier,
    shouldShowPlaceholder: Boolean = items.isEmpty()
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.smallMedium),
        contentPadding = PaddingValues(horizontal = GazegoTheme.spacing.smallMedium)
    ) {
        if (shouldShowPlaceholder) {
            items(count = 20, itemContent = placeholderContent)
        } else {
            items(items = items, itemContent = itemContent)
        }
    }
}