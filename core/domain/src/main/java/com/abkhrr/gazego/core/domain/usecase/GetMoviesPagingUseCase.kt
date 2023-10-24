package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.model.MovieCategory
import javax.inject.Inject

class GetMoviesPagingUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(category: MovieCategory.Categories) =
        repository.getPagingByCategory(category)
}