package com.abkhrr.gazego.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.abkhrr.gazego.R
import com.abkhrr.gazego.core.navigation.GazegoNavigationDestination
import com.abkhrr.gazego.features.discover.navigation.SearchDestination
import com.abkhrr.gazego.features.home.navigation.HomeDestination
import com.abkhrr.gazego.features.wishlist.navigation.WishlistDestination

enum class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val iconResourceId: Int,
    @StringRes val textResourceId: Int
) : GazegoNavigationDestination {
    Home(
        route = HomeDestination.route,
        destination = HomeDestination.destination,
        iconResourceId = R.drawable.ic_home,
        textResourceId = R.string.home
    ),
    Discover(
        route = SearchDestination.route,
        destination = SearchDestination.destination,
        iconResourceId = R.drawable.ic_discover,
        textResourceId = R.string.discover
    ),
    Wishlist(
        route = WishlistDestination.route,
        destination = WishlistDestination.destination,
        iconResourceId = R.drawable.ic_bookmarks,
        textResourceId = R.string.wishlist
    )
}