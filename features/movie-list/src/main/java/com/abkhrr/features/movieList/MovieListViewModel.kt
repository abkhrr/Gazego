package com.abkhrr.features.movieList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.abkhrr.features.movieList.navigation.MovieListDestination
import com.abkhrr.gazego.core.domain.usecase.GetMoviesPagingUseCase
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.ui.mapper.asMovie
import com.abkhrr.gazego.core.ui.mapper.pagingMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesPagingUseCase: GetMoviesPagingUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(getInitialUiState(savedStateHandle))
    val uiState = _uiState.asStateFlow()

    private fun getInitialUiState(savedStateHandle: SavedStateHandle): MovieListUiState {
        val state = MovieListDestination.fromSavedStateHandle(savedStateHandle)

        val movies = getMoviesPagingUseCase(category = state)
            .pagingMap(Movie::asMovie)
            .cachedIn(viewModelScope)

        return MovieListUiState(
            category = state,
            movies = movies
        )
    }
}