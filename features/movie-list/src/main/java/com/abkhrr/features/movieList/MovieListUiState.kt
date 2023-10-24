package com.abkhrr.features.movieList

import androidx.paging.PagingData
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

data class MovieListUiState(
    val category: MovieCategory.Categories,
    val movies: Flow<PagingData<Movie>>,
)