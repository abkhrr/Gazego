package com.abkhrr.gazego.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.abkhrr.gazego.core.common.result.HttpException
import com.abkhrr.gazego.core.common.result.isFailure
import com.abkhrr.gazego.core.common.result.isSuccess
import com.abkhrr.gazego.core.data.mapper.asEntity
import com.abkhrr.gazego.core.data.mapper.asNetworkCategory
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.database.model.MovieRemoteKeyEntity
import com.abkhrr.gazego.core.database.source.MovieDbDataSource
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.network.source.MovieRemoteDataSource
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyClosestToCurrentPosition
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyForFirstItem
import com.abkhrr.gazego.core.data.utils.PagingUtils.getRemoteKeyForLastItem
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val databaseDataSource: MovieDbDataSource,
    private val networkDataSource: MovieRemoteDataSource,
    private val category: MovieCategory.Categories
) : RemoteMediator<Int, MovieEntity>() {
    @ExperimentalPagingApi
    @Suppress("ReturnCount")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
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

            val response = networkDataSource.getByCategory(
                category = category.asNetworkCategory(),
                page = currentPage
            )

            when {
                response.isSuccess() -> {
                    val movies = response.value.results.map { it.asEntity(category) }
                    val endOfPaginationReached = movies.isEmpty()

                    val prevPage = if (currentPage == 1) null else currentPage - 1
                    val nextPage = if (endOfPaginationReached) null else currentPage + 1

                    val remoteKeys = movies.map { entity ->
                        MovieRemoteKeyEntity(
                            id = entity.remoteId,
                            category = category,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    databaseDataSource.handlePaging(
                        category = category,
                        movies = movies,
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
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeyEntity? = getRemoteKeyClosestToCurrentPosition(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndCategory(
            id = entity.remoteId,
            category = category
        )
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeyEntity? = getRemoteKeyForFirstItem(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndCategory(
            id = entity.remoteId,
            category = category
        )
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeyEntity? = getRemoteKeyForLastItem(state) { entity ->
        databaseDataSource.getRemoteKeyByIdAndCategory(
            id = entity.remoteId,
            category = category
        )
    }
}