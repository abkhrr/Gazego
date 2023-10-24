package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String) = repository.search(query)
}