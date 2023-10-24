package com.abkhrr.gazego.core.domain.repository

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideos(movieId: Int): Flow<ApiResult<List<Video>>>
}