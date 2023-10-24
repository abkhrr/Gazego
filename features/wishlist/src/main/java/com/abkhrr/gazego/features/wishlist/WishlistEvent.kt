package com.abkhrr.gazego.features.wishlist

sealed interface WishlistEvent {
    data object RefreshMovies : WishlistEvent
    data object Retry : WishlistEvent
    data object ClearError : WishlistEvent
}