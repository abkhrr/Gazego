package com.abkhrr.gazego.features.discover.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.discover.SearchRoute

object SearchDestination : GazegoNavigationDestination {
    override val route = "search_route"
    override val destination = "search_destination"
}

fun NavGraphBuilder.searchGraph(
    onNavigateToDetailsDestination: (MovieCategory.Details) -> Unit
) = composable(route = SearchDestination.route) {
    SearchRoute(
        onMovieClick = { onNavigateToDetailsDestination(MovieCategory.Details.Movie(it)) }
    )
}