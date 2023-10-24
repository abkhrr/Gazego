package com.abkhrr.gazego.features.reviewlist

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.abkhrr.gazego.core.designsystem.components.GazegoTopAppBar
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.ui.GazegoBackButton
import com.abkhrr.gazego.features.reviewlist.components.ReviewsContainer

@Composable
internal fun ReviewListRoute(
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val reviews = uiState.reviews.collectAsLazyPagingItems()
    ReviewListScreen(
        modifier = modifier,
        reviews = reviews,
        onBackButtonClick = onBackButtonClick
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReviewListScreen(
    reviews: LazyPagingItems<Review>,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            GazegoTopAppBar(
                titleResourceId = R.string.reviews,
                navigationIcon = { GazegoBackButton(onClick = onBackButtonClick) }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Top
        )
    ) { innerPadding ->
        ReviewsContainer(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            reviews = reviews,
        )
    }
}