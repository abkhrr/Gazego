package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int) = repository.getById(id)
}