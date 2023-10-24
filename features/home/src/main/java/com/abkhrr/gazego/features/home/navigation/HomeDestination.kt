package com.abkhrr.gazego.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.home.HomeRoute

object HomeDestination : GazegoNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    onNavigateToDetailsDestination: (MovieCategory.Details) -> Unit,
    onNavigateToListDestination: (MovieCategory.Categories) -> Unit
) = composable(route = HomeDestination.route) {
    HomeRoute(
        onMovieClick = { onNavigateToDetailsDestination(MovieCategory.Details.Movie(it)) },
        onSeeAllClick = { onNavigateToListDestination(it) }
    )
}