package com.abkhrr.gazego.features.discover

import androidx.paging.PagingData
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val query: String = "",
    val isSearching: Boolean = false,
    val searchMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val loadStates: Map<MovieCategory.Categories, Boolean> = emptyMap(),
    val error: Throwable? = null,
    val isOfflineModeAvailable: Boolean = false
)

internal val SearchUiState.isLoading: Boolean get() = loadStates.values.any { it }