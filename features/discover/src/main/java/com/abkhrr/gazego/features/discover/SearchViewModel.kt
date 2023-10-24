package com.abkhrr.gazego.features.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.abkhrr.gazego.core.domain.usecase.SearchMovieUseCase
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.ui.handler.EventHandler
import com.abkhrr.gazego.core.ui.mapper.asMovie
import com.abkhrr.gazego.core.ui.mapper.pagingMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("TooManyFunctions")
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMovieUseCase,
) : ViewModel(), EventHandler<SearchEvent> {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    override fun onEvent(event: SearchEvent) = when (event) {
        is SearchEvent.ChangeQuery -> onQueryChange(event.value)
        SearchEvent.Refresh -> onRefresh()
        SearchEvent.Retry -> onRetry()
        SearchEvent.ClearError -> onClearError()
    }

    private fun onQueryChange(query: String) {
        val isSearching = query.isNotBlank()
        _uiState.update {
            it.copy(query = query, isSearching = isSearching)
        }
        searchDebounced(query)
    }

    private fun searchDebounced(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DURATION)
            search(query)
        }
    }

    private fun search(query: String) = _uiState.update {
        val searchMovies = if (it.isSearching) {
            searchMoviesUseCase(query)
                .pagingMap(Movie::asMovie)
                .cachedIn(viewModelScope)
        } else {
            emptyFlow()
        }

        it.copy(
            searchMovies = searchMovies
        )
    }

    private fun onRefresh() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    private fun onRetry() {
        onClearError()
        onRefresh()
    }

    private fun onClearError() = _uiState.update { it.copy(error = null) }
}

private const val SEARCH_DEBOUNCE_DURATION = 500L