package com.abkhrr.gazego.features.reviewlist

import androidx.paging.PagingData
import com.abkhrr.gazego.core.model.Review
import kotlinx.coroutines.flow.Flow

data class ReviewListUiState(
    val movieId: Int,
    val reviews: Flow<PagingData<Review>>
)