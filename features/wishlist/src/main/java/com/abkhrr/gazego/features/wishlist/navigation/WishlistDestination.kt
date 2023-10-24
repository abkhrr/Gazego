package com.abkhrr.gazego.features.wishlist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.wishlist.WishlistRoute

object WishlistDestination : GazegoNavigationDestination {
    override val route = "wishlist_route"
    override val destination = "wishlist_destination"
}

fun NavGraphBuilder.wishlistGraph(
    onNavigateToDetailsDestination: (MovieCategory.Details) -> Unit
) = composable(route = WishlistDestination.route) {
    WishlistRoute(
        onMovieClick = { onNavigateToDetailsDestination(MovieCategory.Details.Movie(it)) }
    )
}