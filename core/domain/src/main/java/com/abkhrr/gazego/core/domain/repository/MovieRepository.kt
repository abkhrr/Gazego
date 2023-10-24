package com.abkhrr.gazego.core.domain.repository

import androidx.paging.PagingData
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // List
    fun getByCategory(
        category: MovieCategory.Categories,
        primaryReleaseYear: Int,
        primaryMinReleaseDate: String,
        primaryMaxReleaseDate: String
    ): Flow<ApiResult<List<Movie>>>
    fun getPagingByCategory(category: MovieCategory.Categories): Flow<PagingData<Movie>>
    fun search(query: String): Flow<PagingData<Movie>>

    // Details
    suspend fun getById(id: Int): Flow<ApiResult<MovieDetails?>>
    suspend fun getByIds(ids: List<Int>): Flow<ApiResult<List<MovieDetails>>>
}