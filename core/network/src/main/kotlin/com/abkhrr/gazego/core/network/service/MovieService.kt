package com.abkhrr.gazego.core.network.service

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.network.model.NetworkMovie
import com.abkhrr.gazego.core.network.model.NetworkMovieDetail
import com.abkhrr.gazego.core.network.response.MovieResponse
import com.abkhrr.gazego.core.network.util.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(Constant.Endpoint.DISCOVER_UPCOMING)
    suspend fun upcomingMovies(
        @Query(Constant.Query.INCLUDE_ADULT) includeAdult: Boolean = false,
        @Query(Constant.Query.INCLUDE_VIDEO) includeVideo: Boolean = false,
        @Query(Constant.Query.LANGUAGE) language: String = Constant.DEFAULT_LANG,
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE,
        @Query(Constant.Query.PRIMARY_RELEASE_YEAR) primaryReleaseYear: Int = 2023,
        @Query(Constant.Query.PRIMARY_RELEASE_DATE_GTE) primaryReleaseDateGte: String,
        @Query(Constant.Query.PRIMARY_RELEASE_DATE_LTE) primaryReleaseDateLte: String,
        @Query(Constant.Query.SORT_BY) sortBy: String = "popularity.desc"
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.TOP_RATED_MOVIE)
    suspend fun getTopRated(
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.POPULAR_MOVIE)
    suspend fun getPopular(
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.NOW_PLAYING_MOVIE)
    suspend fun getNowPlaying(
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.DISCOVER_MOVIE)
    suspend fun getDiscover(
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.TRENDING_MOVIE)
    suspend fun getTrending(
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

    @GET(Constant.Endpoint.DETAILS_MOVIE)
    suspend fun getDetailsById(
        @Path(Constant.Path.MOVIE_ID) id: Int,
        @Query(Constant.Query.APPEND_TO_RESPONSE) appendToResponse: String = Constant.Query.DETAILS_APPEND_TO_RESPONSE
    ): ApiResult<NetworkMovieDetail>

    @GET(Constant.Endpoint.SEARCH_MOVIE)
    suspend fun search(
        @Query(Constant.Query.QUERY) query: String,
        @Query(Constant.Query.PAGE) page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse>

}