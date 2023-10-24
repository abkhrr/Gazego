package com.abkhrr.gazego.features.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abkhrr.gazego.core.common.result.handle
import com.abkhrr.gazego.core.domain.usecase.AddMovieToWishlistUseCase
import com.abkhrr.gazego.core.domain.usecase.GetMovieDetailsUseCase
import com.abkhrr.gazego.core.domain.usecase.GetReviewPagingUseCase
import com.abkhrr.gazego.core.domain.usecase.RemoveMovieFromWishlistUseCase
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.ui.handler.EventHandler
import com.abkhrr.gazego.core.ui.mapper.asMovieDetails
import com.abkhrr.gazego.core.ui.mapper.asReview
import com.abkhrr.gazego.core.ui.mapper.pagingMap
import com.abkhrr.gazego.features.details.navigation.DetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.paging.cachedIn
import com.abkhrr.gazego.core.domain.usecase.GetReviewListUseCase
import com.abkhrr.gazego.core.domain.usecase.GetVideoUseCase
import javax.inject.Inject

@Suppress("LongParameterList", "TooManyFunctions")
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieToWishlistUseCase: AddMovieToWishlistUseCase,
    private val removeMovieFromWishlistUseCase: RemoveMovieFromWishlistUseCase,
    private val getReviewListUseCase: GetReviewListUseCase,
    private val getVideoUseCase: GetVideoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), EventHandler<DetailsEvent> {
    private val _uiState = MutableStateFlow(getInitialUiState(savedStateHandle))
    val uiState = _uiState.asStateFlow()

    private var contentJob = loadContent()

    override fun onEvent(event: DetailsEvent) = when (event) {
        DetailsEvent.Wishlist -> onWishlistMovie()
        DetailsEvent.Refresh -> onRefresh()
        DetailsEvent.Retry -> onRetry()
        DetailsEvent.ClearError -> onClearError()
        DetailsEvent.ClearUserMessage -> onClearUserMessage()
    }

    private fun getInitialUiState(savedStateHandle: SavedStateHandle): DetailsUIState {
        val category = DetailsDestination.fromSavedStateHandle(savedStateHandle)
        return DetailsUIState(category = category)
    }

    private fun loadContent() = when (val category = uiState.value.category) {
        is MovieCategory.Details.Movie -> {
            loadMovie(category.id)
            loadMovieReviews(category.id)
            loadMovieVideos(category.id)
        }
    }

    private fun onWishlistMovie() {
        _uiState.update {
            it.copy(movie = it.movie?.copy(isOnWishlist = !it.movie.isOnWishlist))
        }
        viewModelScope.launch {
            uiState.value.movie?.let { movie ->
                if (movie.isOnWishlist) {
                    addMovieToWishlistUseCase(movie.id)
                } else {
                    removeMovieFromWishlistUseCase(movie.id)
                }
            }
        }
    }

    private fun onRefresh() {
        contentJob.cancel()
        contentJob = loadContent()
    }

    private fun onRetry() {
        onClearError()
        onRefresh()
    }

    private fun onClearError() = _uiState.update { it.copy(error = null) }
    private fun onClearUserMessage() = _uiState.update { it.copy(message = null) }

    private fun loadMovie(id: Int) = viewModelScope.launch {
        getMovieDetailsUseCase(id).handle {
            onLoading { movie ->
                _uiState.update { it.copy(movie = movie?.asMovieDetails(), isLoading = true) }
            }
            onSuccess { movie ->
                _uiState.update { it.copy(movie = movie?.asMovieDetails(), isLoading = false) }
            }
            onFailure(::handleFailure)
        }
    }

    private fun loadMovieReviews(id: Int) = viewModelScope.launch {
        getReviewListUseCase(id).handle {
            onLoading { review ->
                _uiState.update { it.copy(review = review, isLoading = true) }
            }
            onSuccess { review ->
                _uiState.update { it.copy(review = review, isLoading = false) }
            }
            onFailure(::handleFailure)
        }
    }

    private fun loadMovieVideos(id: Int) = viewModelScope.launch {
        getVideoUseCase(id).handle {
            onLoading { videos ->
                _uiState.update { it.copy(videos = videos, isLoading = true) }
            }
            onSuccess { videos ->
                _uiState.update { it.copy(videos = videos, isLoading = false) }
            }
            onFailure(::handleFailure)
        }
    }

    private fun handleFailure(error: Throwable) = _uiState.update {
        Log.e("ERROR", error.message.toString())
        it.copy(
            error = error,
            isOfflineModeAvailable = it.movie != null,
            isLoading = false
        )
    }
}