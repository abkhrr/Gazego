package com.abkhrr.gazego.features.reviewlist.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.reviewlist.ReviewListRoute

object ReviewListDestination : GazegoNavigationDestination {
    override val route = "review_list_route"
    override val destination = "review_list_destination"

    const val ID = "movie_id"
    val routeWithArgument = "$route/{$ID}"

    fun createNavigationRoute(category: MovieCategory.Reviews) = "$route/${category.movieId}"

    fun fromSavedStateHandle(savedStateHandle: SavedStateHandle) = MovieCategory.Reviews.from(
        id = checkNotNull(savedStateHandle[ID]) { "ARGUMENT Movie Id Should Not Null." }
    )
}

fun NavGraphBuilder.reviewListGraph(
    onBackButtonClick: () -> Unit
) = composable(
    route = ReviewListDestination.routeWithArgument,
    arguments = listOf(
        navArgument(ReviewListDestination.ID) { type = NavType.IntType }
    )
) {
    ReviewListRoute(
        onBackButtonClick = onBackButtonClick
    )
}