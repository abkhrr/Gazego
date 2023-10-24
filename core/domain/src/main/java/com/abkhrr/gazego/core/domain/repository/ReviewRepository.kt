package com.abkhrr.gazego.core.domain.repository

import androidx.paging.PagingData
import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun getByMovieId(movieId: Int): Flow<ApiResult<List<Review>>>
    fun getPagingByMovieId(movieId: Int, page: Int = 1): Flow<PagingData<Review>>
}