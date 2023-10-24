package com.abkhrr.gazego.features.reviewlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.abkhrr.gazego.core.domain.usecase.GetReviewPagingUseCase
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.ui.mapper.asReview
import com.abkhrr.gazego.core.ui.mapper.pagingMap
import com.abkhrr.gazego.features.reviewlist.navigation.ReviewListDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val getReviewPagingUseCase: GetReviewPagingUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(getInitialUiState(savedStateHandle))
    val uiState = _uiState.asStateFlow()

    private fun getInitialUiState(savedStateHandle: SavedStateHandle): ReviewListUiState {
        val state = ReviewListDestination.fromSavedStateHandle(savedStateHandle)
        val id = state.id

        val reviews = getReviewPagingUseCase(movieId = id)
            .pagingMap(Review::asReview)
            .cachedIn(viewModelScope)

        return ReviewListUiState(
            movieId = id,
            reviews = reviews
        )
    }
}