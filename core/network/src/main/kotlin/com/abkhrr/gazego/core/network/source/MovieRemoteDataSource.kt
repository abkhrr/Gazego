package com.abkhrr.gazego.core.network.source

import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.common.result.isFailure
import com.abkhrr.gazego.core.common.result.isSuccess
import com.abkhrr.gazego.core.network.model.NetworkMovie
import com.abkhrr.gazego.core.network.model.NetworkMovieCategory
import com.abkhrr.gazego.core.network.model.NetworkMovieDetail
import com.abkhrr.gazego.core.network.response.MovieResponse
import com.abkhrr.gazego.core.network.service.MovieService
import com.abkhrr.gazego.core.network.util.Constant
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val service: MovieService) {
    suspend fun getByCategory(
        category: NetworkMovieCategory.Categories,
        page: Int = Constant.DEFAULT_PAGE,
        primaryReleaseYear: Int = 2023,
        primaryMinReleaseDate: String = emptyString(),
        primaryMaxReleaseDate: String = emptyString()
    ): ApiResult<MovieResponse> = when (category) {
        NetworkMovieCategory.Categories.Upcoming -> {
            service.upcomingMovies(
                page = page,
                primaryReleaseYear = primaryReleaseYear,
                primaryReleaseDateGte = primaryMinReleaseDate,
                primaryReleaseDateLte = primaryMaxReleaseDate
            )
        }
        NetworkMovieCategory.Categories.TopRated -> service.getTopRated(page)
        NetworkMovieCategory.Categories.Popular -> service.getPopular(page)
        NetworkMovieCategory.Categories.Trending -> service.getTrending(page)
        NetworkMovieCategory.Categories.Discover -> service.getDiscover(page)
        NetworkMovieCategory.Categories.NowPlaying -> service.getNowPlaying(page)
    }

    suspend fun getPagingByCategory(
        category: NetworkMovieCategory.Common,
        page: Int = Constant.DEFAULT_PAGE,
        primaryReleaseYear: Int = 2023,
        primaryMinReleaseDate: String = emptyString(),
        primaryMaxReleaseDate: String = emptyString()
    ): ApiResult<MovieResponse> = when (category) {
        NetworkMovieCategory.Common.Upcoming -> {
            service.upcomingMovies(
                page = page,
                primaryReleaseYear = primaryReleaseYear,
                primaryReleaseDateGte = primaryMinReleaseDate,
                primaryReleaseDateLte = primaryMaxReleaseDate
            )
        }
        NetworkMovieCategory.Common.TopRated -> service.getTopRated(page)
        NetworkMovieCategory.Common.Popular -> service.getPopular(page)
        NetworkMovieCategory.Common.Trending -> service.getTrending(page)
        NetworkMovieCategory.Common.Discover -> service.getDiscover(page)
        NetworkMovieCategory.Common.NowPlaying -> service.getNowPlaying(page)
    }

    suspend fun search(
        query: String,
        page: Int = Constant.DEFAULT_PAGE
    ): ApiResult<MovieResponse> = service.search(query, page)

    suspend fun getById(
        id: Int,
        appendToResponse: String = Constant.Query.DETAILS_APPEND_TO_RESPONSE
    ): ApiResult<NetworkMovieDetail> = service.getDetailsById(id, appendToResponse)

    suspend fun getByIds(
        ids: List<Int>,
        appendToResponse: String = Constant.Query.DETAILS_APPEND_TO_RESPONSE
    ): ApiResult<List<NetworkMovieDetail>> {
        val movies = ids.map { id ->
            val response = service.getDetailsById(id, appendToResponse)

            when {
                response.isSuccess() -> response.value
                response.isFailure() -> return ApiResult.failure(response.error)
                else -> error("Unhandled: $response")
            }
        }

        return ApiResult.success(movies)
    }
}