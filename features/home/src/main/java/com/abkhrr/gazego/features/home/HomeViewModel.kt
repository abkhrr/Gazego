package com.abkhrr.gazego.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abkhrr.gazego.core.common.result.handle
import com.abkhrr.gazego.core.common.utils.DateTime
import com.abkhrr.gazego.core.domain.usecase.GetMovieListUseCase
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.ui.handler.EventHandler
import com.abkhrr.gazego.core.ui.mapper.asMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieListUseCase,
): ViewModel(), EventHandler<HomeEvent> {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadContents()
    }

    override fun onEvent(event: HomeEvent) = when(event) {
        HomeEvent.Refresh -> onRefresh()
        HomeEvent.Retry -> onRetry()
        HomeEvent.ClearError -> onClearError()
    }

    private fun loadContents() {
        val category = listOf(
            MovieCategory.Categories.Upcoming,
            MovieCategory.Categories.TopRated,
            MovieCategory.Categories.Popular,
            MovieCategory.Categories.NowPlaying
        )
        category.forEach(::loadMovies)
    }

    private fun onRefresh() {
        viewModelScope.coroutineContext.cancelChildren()
        loadContents()
    }

    private fun onClearError() = _uiState.update { it.copy(error = null) }

    private fun onRetry() {
        onClearError()
        onRefresh()
    }

    private fun loadMovies(category: MovieCategory.Categories) = viewModelScope.launch {
        val currentYear = DateTime.getCurrentYear()
        val minReleaseDate = DateTime.getCurrentDateString()
        val maxReleaseDate = DateTime.getFutureDateString(plus = 1)

        getMoviesUseCase(
            category = category,
            primaryReleaseYear = currentYear,
            primaryMinReleaseDate = minReleaseDate,
            primaryMaxReleaseDate = maxReleaseDate
        ).handle {
            onLoading { movies ->
                _uiState.update {
                    it.copy(
                        movies = it.movies + (
                           category to movies?.map(Movie::asMovie).orEmpty()
                        ),
                        loadStates = it.loadStates + (category to true)
                    )
                }
            }
            onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        movies = it.movies + (
                           category to movies.map(Movie::asMovie)
                        ),
                        loadStates = it.loadStates + (category to false)
                    )
                }
            }
            onFailure { error -> handleFailure(error, category) }
        }
    }

    private fun handleFailure(error: Throwable, category: MovieCategory.Categories) =
        _uiState.update {
            it.copy(
                error = error,
                isOfflineModeAvailable = it.movies.values.all(List<Movie>::isNotEmpty),
                loadStates = it.loadStates + (category to false)
            )
        }
}