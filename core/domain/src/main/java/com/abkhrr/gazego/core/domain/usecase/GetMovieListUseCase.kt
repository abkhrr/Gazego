package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.model.MovieCategory
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
     operator fun invoke(
         category: MovieCategory.Categories,
         primaryReleaseYear: Int = 2023,
         primaryMinReleaseDate: String,
         primaryMaxReleaseDate: String
     ) = repository.getByCategory(
         category = category,
         primaryReleaseYear = primaryReleaseYear,
         primaryMinReleaseDate = primaryMinReleaseDate,
         primaryMaxReleaseDate = primaryMaxReleaseDate
     )
}