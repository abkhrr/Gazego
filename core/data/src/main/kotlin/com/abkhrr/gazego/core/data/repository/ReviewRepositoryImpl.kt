package com.abkhrr.gazego.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.data.mapper.asEntity
import com.abkhrr.gazego.core.data.mapper.asModel
import com.abkhrr.gazego.core.data.paging.ReviewRemoteMediator
import com.abkhrr.gazego.core.data.utils.defaultPagingConfig
import com.abkhrr.gazego.core.data.utils.listMap
import com.abkhrr.gazego.core.data.utils.pagingMap
import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.source.ReviewDbDataSource
import com.abkhrr.gazego.core.domain.repository.ReviewRepository
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.network.flow.networkBoundResource
import com.abkhrr.gazego.core.network.source.ReviewRemoteDataSource
import com.abkhrr.gazego.core.network.util.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val dataSource: ReviewRemoteDataSource,
    private val databaseDataSource: ReviewDbDataSource
) : ReviewRepository {
    override suspend fun getByMovieId(movieId: Int): Flow<ApiResult<List<Review>>> =
        networkBoundResource(
            query = {
                databaseDataSource.getByMovie(
                    movieId = movieId,
                    pageSize = PAGE_SIZE
                ).listMap(ReviewEntity::asModel)
            },
            fetch = { dataSource.getMovieReviews(movieId) },
            saveFetchResult = { response ->
                databaseDataSource.deleteAndInsertAll(
                    movieId = movieId,
                    reviews = response.results.map { it.asEntity(movieId) }
                )
            }
        )

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagingByMovieId(movieId: Int, page: Int): Flow<PagingData<Review>> {
        return Pager(
            config = defaultPagingConfig,
            remoteMediator = ReviewRemoteMediator(movieId, databaseDataSource, dataSource),
            pagingSourceFactory = { databaseDataSource.getPagingByMovieId(movieId) }
        ).flow.pagingMap(ReviewEntity::asModel)
    }
}