package com.abkhrr.features.movieList.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abkhrr.features.movieList.MovieListRoute
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination

object MovieListDestination : GazegoNavigationDestination {
    override val route = "movie_list_route"
    override val destination = "movie_list_destination"

    const val categoryArgument = "category"
    val routeWithArgument = "$route/{$categoryArgument}"

    fun createNavigationRoute(category: MovieCategory.Categories) = "$route/${category.category}"

    fun fromSavedStateHandle(savedStateHandle: SavedStateHandle) = MovieCategory.Categories[
        checkNotNull(savedStateHandle[categoryArgument]) { MediaTypeNullMessage }
    ]
}

fun NavGraphBuilder.movieListGraph(
    onBackButtonClick: () -> Unit,
    onNavigateToDetailsDestination: (MovieCategory.Details) -> Unit
) = composable(
    route = MovieListDestination.routeWithArgument,
    arguments = listOf(
        navArgument(MovieListDestination.categoryArgument) { type = NavType.StringType }
    )
) {
    MovieListRoute(
        onBackButtonClick = onBackButtonClick,
        onMovieClick = { onNavigateToDetailsDestination(MovieCategory.Details.Movie(it)) },
    )
}

private const val MediaTypeNullMessage = "Media type is null."