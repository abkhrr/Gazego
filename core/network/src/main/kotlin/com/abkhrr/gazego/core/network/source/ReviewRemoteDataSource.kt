package com.abkhrr.gazego.core.network.source

import com.abkhrr.gazego.core.network.service.ReviewService
import com.abkhrr.gazego.core.network.util.DEFAULT_PAGE
import javax.inject.Inject

class ReviewRemoteDataSource @Inject constructor(private val service: ReviewService) {
    suspend fun getMovieReviews(
        movieId: Int,
        page: Int = DEFAULT_PAGE
    ) = service.getReviews(movieId = movieId, page = page)
}