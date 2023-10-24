package com.abkhrr.gazego.features.details.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.details.DetailsRoute

object DetailsDestination : GazegoNavigationDestination {
    override val route = "details_route"
    override val destination = "details_destination"

    const val idArgument = "id"
    const val categoryTypeArgument = "category"
    val routeWithArguments = "$route/{$idArgument}/{$categoryTypeArgument}"

    fun createNavigationRoute(category: MovieCategory.Details) =
        "$route/${category.mediaId}/${category.mediaType}"

    fun fromSavedStateHandle(savedStateHandle: SavedStateHandle) = MovieCategory.Details.from(
        id = checkNotNull(savedStateHandle[idArgument]) { MediaIdNullMessage },
        category = checkNotNull(savedStateHandle[categoryTypeArgument]) { MediaTypeNullMessage }
    )
}

fun NavGraphBuilder.detailsGraph(
    onBackButtonClick: () -> Unit,
    onNavigateToReviewListDestination: (MovieCategory.Reviews) -> Unit,
    onShowMessage: (String) -> Unit
) = composable(
    route = DetailsDestination.routeWithArguments,
    arguments = listOf(
        navArgument(DetailsDestination.idArgument) { type = NavType.IntType },
        navArgument(DetailsDestination.categoryTypeArgument) { type = NavType.StringType }
    )
) {
    DetailsRoute(
        onBackButtonClick = onBackButtonClick,
        onSeeAllReviewsClick = { onNavigateToReviewListDestination(MovieCategory.Reviews.MovieReviews(it)) },
        onShowMessage = onShowMessage
    )
}

private const val MediaIdNullMessage = "Category Is Null."
private const val MediaTypeNullMessage = "Category Is Null."