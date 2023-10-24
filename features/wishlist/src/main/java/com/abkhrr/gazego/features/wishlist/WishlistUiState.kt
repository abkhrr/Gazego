package com.abkhrr.gazego.features.wishlist

import com.abkhrr.gazego.core.model.MovieDetails

data class WishlistUiState(
    val movies: List<MovieDetails> = emptyList(),
    val isMoviesLoading: Boolean = false,
    val error: Throwable? = null,
    val isOfflineModeAvailable: Boolean = false
)