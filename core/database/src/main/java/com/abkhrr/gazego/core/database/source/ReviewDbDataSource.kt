package com.abkhrr.gazego.core.database.source

import androidx.paging.PagingSource
import com.abkhrr.gazego.core.database.dao.ReviewDao
import com.abkhrr.gazego.core.database.dao.ReviewRemoteKeyDao
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.database.model.MovieRemoteKeyEntity
import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.model.ReviewRemoteKeysEntity
import com.abkhrr.gazego.core.database.utils.GazegoDatabaseTransactionProvider
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewDbDataSource @Inject constructor(
    private val reviewDao: ReviewDao,
    private val reviewRemoteKeyDao: ReviewRemoteKeyDao,
    private val transactionProvider: GazegoDatabaseTransactionProvider
) {
    fun getByMovie(movieId: Int, pageSize: Int): Flow<List<ReviewEntity>> =
        reviewDao.getByMovie(movieId, pageSize)

    fun getPagingByMovieId(movieId: Int): PagingSource<Int, ReviewEntity> =
        reviewDao.getPagingByMovieId(movieId)

    suspend fun insertAll(reviews: List<ReviewEntity>) {
        transactionProvider.runWithTransaction {
            reviews.forEach { review ->
                reviewDao.insert(review)
            }
        }
    }

    suspend fun deleteAndInsertAll(reviews: List<ReviewEntity>, movieId: Int) =
        transactionProvider.runWithTransaction {
            reviewDao.deleteByMovie(movieId = movieId)

            reviews.forEach { review ->
                reviewDao.insert(review)
            }
        }

    suspend fun getRemoteKeyByIdAndMovieId(id: Int, movieId: Int) =
        reviewRemoteKeyDao.getByIdAndMovieId(id, movieId)

    suspend fun handlePaging(
        movieId: Int,
        reviews: List<ReviewEntity>,
        remoteKeys: List<ReviewRemoteKeysEntity>,
        shouldDeleteMoviesAndRemoteKeys: Boolean
    ) = transactionProvider.runWithTransaction {
        if (shouldDeleteMoviesAndRemoteKeys) {
            reviewDao.deleteByMovie(movieId)
            reviewRemoteKeyDao.deleteByMovieId(movieId)
        }
        reviewRemoteKeyDao.insertAll(remoteKeys)
        reviewDao.insertAll(reviews)
    }
}