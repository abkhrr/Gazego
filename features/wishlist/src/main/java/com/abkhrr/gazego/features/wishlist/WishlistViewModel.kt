package com.abkhrr.gazego.features.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abkhrr.gazego.core.common.result.handle
import com.abkhrr.gazego.core.domain.usecase.GetAllWishlistedMoviesUseCase
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.ui.handler.EventHandler
import com.abkhrr.gazego.core.ui.mapper.asMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getWishlistMoviesUseCase: GetAllWishlistedMoviesUseCase
) : ViewModel(), EventHandler<WishlistEvent> {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState = _uiState.asStateFlow()

    private var job = getMoviesJob()

    override fun onEvent(event: WishlistEvent) = when (event) {
        WishlistEvent.RefreshMovies -> onRefresh()
        WishlistEvent.Retry -> onRetry()
        WishlistEvent.ClearError -> onClearError()
    }

    private fun onRefresh() {
        job.cancel()
        job = getMoviesJob()
    }

    private fun onRetry() {
        onClearError()
        onRefresh()
    }

    private fun onClearError() = _uiState.update { it.copy(error = null) }

    private fun getMoviesJob() = viewModelScope.launch {
        getWishlistMoviesUseCase().handle {
            onLoading { movies ->
                _uiState.update {
                    it.copy(
                        movies = movies?.map(MovieDetails::asMovieDetails).orEmpty(),
                        isMoviesLoading = true
                    )
                }
            }
            onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        movies = movies.map(MovieDetails::asMovieDetails),
                        isMoviesLoading = false
                    )
                }
            }
            onFailure(::handleFailure)
        }
    }

    private fun handleFailure(error: Throwable) = _uiState.update {
        it.copy(
            error = error,
            isOfflineModeAvailable = it.movies.isNotEmpty(),
            isMoviesLoading = false
        )
    }
}