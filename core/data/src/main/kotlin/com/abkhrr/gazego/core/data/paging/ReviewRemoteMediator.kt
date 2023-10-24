package com.abkhrr.gazego.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.abkhrr.gazego.core.common.result.HttpException
import com.abkhrr.gazego.core.common.result.isFailure
import com.abkhrr.gazego.core.common.result.isSuccess
import com.abkhrr.gazego.core.data.mapper.asEntity
import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.model.ReviewRemoteKeysEntity
import com.abkhrr.gazego.core.database.source.ReviewDbDataSource
import com.abkhrr.gazego.core.network.source.ReviewRemoteDataSource
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyClosestToCurrentPosition
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyForFirstItem
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyForLastItem
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ReviewRemoteMediator(
    private val movieId: Int,
    private val databaseDataSource: ReviewDbDataSource,
    private val networkDataSource: ReviewRemoteDataSource,
) : RemoteMediator<Int, ReviewEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReviewEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextPage
                }
            }

            val response = networkDataSource.getMovieReviews(
                movieId = movieId,
                page = currentPage
            )

            when {
                response.isSuccess() -> {
                    val reviews = response.value.results.map { it.asEntity(movieId) }
                    val endOfPaginationReached = reviews.isEmpty()

                    val prevPage = if (currentPage == 1) null else currentPage - 1
                    val nextPage = if (endOfPaginationReached) null else currentPage + 1

                    val remoteKeys = reviews.map { entity ->
                        ReviewRemoteKeysEntity(
                            id = entity.id,
                            movieId = entity.movieId,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    databaseDataSource.handlePaging(
                        movieId = movieId,
                        reviews = reviews,
                        remoteKeys = remoteKeys,
                        shouldDeleteMoviesAndRemoteKeys = loadType == LoadType.REFRESH
                    )

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
                response.isFailure() -> return MediatorResult.Error(response.error)
                else -> error("Error: $response")
            }
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ReviewEntity>
    ): ReviewRemoteKeysEntity? = getRemoteKeyClosestToCurrentPosition(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndMovieId(
            id = entity.id,
            movieId = entity.movieId
        )
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ReviewEntity>
    ): ReviewRemoteKeysEntity? = getRemoteKeyForFirstItem(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndMovieId(
            id = entity.id,
            movieId = entity.movieId
        )
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ReviewEntity>
    ): ReviewRemoteKeysEntity? = getRemoteKeyForLastItem(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndMovieId(
            id = entity.id,
            movieId = entity.movieId
        )
    }
}