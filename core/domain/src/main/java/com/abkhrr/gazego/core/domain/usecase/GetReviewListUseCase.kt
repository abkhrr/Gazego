package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewListUseCase @Inject constructor(private val repository: ReviewRepository) {
    suspend operator fun invoke(movieId: Int) = repository.getByMovieId(movieId)
}