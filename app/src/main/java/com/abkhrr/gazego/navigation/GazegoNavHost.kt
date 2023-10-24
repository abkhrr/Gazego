package com.abkhrr.gazego.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abkhrr.features.movieList.navigation.MovieListDestination
import com.abkhrr.features.movieList.navigation.movieListGraph
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.details.navigation.DetailsDestination
import com.abkhrr.gazego.features.details.navigation.detailsGraph
import com.abkhrr.gazego.features.discover.navigation.searchGraph
import com.abkhrr.gazego.features.home.navigation.homeGraph
import com.abkhrr.gazego.features.reviewlist.navigation.ReviewListDestination
import com.abkhrr.gazego.features.reviewlist.navigation.reviewListGraph
import com.abkhrr.gazego.features.wishlist.navigation.wishlistGraph

@Composable
fun GazegoNavHost(
    navController: NavHostController,
    startDestination: GazegoNavigationDestination,
    onNavigateToDestination: (GazegoNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {
        homeGraph(
            onNavigateToListDestination = {
                onNavigateToDestination(
                    MovieListDestination,
                    MovieListDestination.createNavigationRoute(it)
                )
            },
            onNavigateToDetailsDestination = {
                onNavigateToDestination(
                    DetailsDestination,
                    DetailsDestination.createNavigationRoute(it)
                )
            }
        )
        detailsGraph(
            onBackButtonClick = onBackClick,
            onShowMessage = onShowMessage,
            onNavigateToReviewListDestination = {
                onNavigateToDestination(
                    ReviewListDestination,
                    ReviewListDestination.createNavigationRoute(it)
                )
            }
        )
        reviewListGraph(onBackButtonClick = onBackClick)
        movieListGraph(
            onBackButtonClick = onBackClick,
            onNavigateToDetailsDestination = {
                onNavigateToDestination(
                    DetailsDestination,
                    DetailsDestination.createNavigationRoute(it)
                )
            }
        )
        searchGraph(
            onNavigateToDetailsDestination = {
                onNavigateToDestination(
                    DetailsDestination,
                    DetailsDestination.createNavigationRoute(it)
                )
            }
        )
        wishlistGraph(
            onNavigateToDetailsDestination = {
                onNavigateToDestination(
                    DetailsDestination,
                    DetailsDestination.createNavigationRoute(it)
                )
            }
        )
    }
}