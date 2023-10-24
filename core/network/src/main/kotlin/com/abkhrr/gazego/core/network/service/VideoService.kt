package com.abkhrr.gazego.core.network.service

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.network.response.VideoResponse
import com.abkhrr.gazego.core.network.util.Constant
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {
    @GET(Constant.Endpoint.VIDEO)
    suspend fun getVideos(
        @Path(Constant.Path.MOVIE_ID) movieId: Int
    ): ApiResult<VideoResponse>
}