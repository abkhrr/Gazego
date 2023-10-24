package com.abkhrr.gazego.core.network.source

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.network.response.VideoResponse
import com.abkhrr.gazego.core.network.service.VideoService
import javax.inject.Inject

class VideoRemoteDataSource @Inject constructor(
    private val service: VideoService
) {
    suspend fun getVideos(movieId: Int): ApiResult<VideoResponse> =
        service.getVideos(movieId)
}