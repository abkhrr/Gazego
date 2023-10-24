package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewPagingUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(movieId: Int) =
        repository.getPagingByMovieId(movieId)
}