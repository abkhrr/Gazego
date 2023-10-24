package com.abkhrr.gazego.core.database.source

import androidx.paging.PagingSource
import com.abkhrr.gazego.core.database.dao.MovieDao
import com.abkhrr.gazego.core.database.dao.MoviesRemoteKeysDao
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.database.model.MovieRemoteKeyEntity
import com.abkhrr.gazego.core.database.utils.GazegoDatabaseTransactionProvider
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDbDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRemoteKeyDao: MoviesRemoteKeysDao,
    private val transactionProvider: GazegoDatabaseTransactionProvider
) {
    fun getByCategory(category: MovieCategory.Categories, pageSize: Int): Flow<List<MovieEntity>> =
        movieDao.getByCategory(category, pageSize)

    fun getPagingByMediaType(category: MovieCategory.Categories): PagingSource<Int, MovieEntity> =
        movieDao.getPagingByCategory(category)

    suspend fun insertAll(movies: List<MovieEntity>) = movieDao.insertAll(movies)

    suspend fun deleteByCategoryThenInsertAll(
        category: MovieCategory.Categories,
        movies: List<MovieEntity>
    ) = transactionProvider.runWithTransaction {
        movieDao.deleteByCategory(category)
        movieDao.insertAll(movies)
    }

    suspend fun getRemoteKeyByIdAndCategory(id: Int, category: MovieCategory.Categories) =
        movieRemoteKeyDao.getByIdAndCategory(id, category)

    suspend fun handlePaging(
        category: MovieCategory.Categories,
        movies: List<MovieEntity>,
        remoteKeys: List<MovieRemoteKeyEntity>,
        shouldDeleteMoviesAndRemoteKeys: Boolean
    ) = transactionProvider.runWithTransaction {
        if (shouldDeleteMoviesAndRemoteKeys) {
            movieDao.deleteByCategory(category)
            movieRemoteKeyDao.deleteByCategory(category)
        }
        movieRemoteKeyDao.insertAll(remoteKeys)
        movieDao.insertAll(movies)
    }
}