package com.abkhrr.gazego.features.details

import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.ui.model.Message

data class DetailsUIState(
    val category: MovieCategory.Details,
    val movie: MovieDetails? = null,
    val videos: List<Video>? = emptyList(),
    val review: List<Review>? = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val message: Message? = null,
    val isOfflineModeAvailable: Boolean = false
)