package com.abkhrr.gazego.core.network.service

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.network.response.ReviewResponse
import com.abkhrr.gazego.core.network.util.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET(Constant.Endpoint.MOVIE_REVIEWS)
    suspend fun getReviews(
        @Path(Constant.Path.MOVIE_ID) movieId: Int,
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ) : ApiResult<ReviewResponse>
}