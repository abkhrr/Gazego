package com.abkhrr.gazego.features.home

import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory

data class HomeUiState(
    val movies: Map<MovieCategory.Categories, List<Movie>> = emptyMap(),
    val loadStates: Map<MovieCategory, Boolean> = emptyMap(),
    val error: Throwable? = null,
    val isOfflineModeAvailable: Boolean = false
)

internal val HomeUiState.isLoading: Boolean
    get() = loadStates.values.any { it }