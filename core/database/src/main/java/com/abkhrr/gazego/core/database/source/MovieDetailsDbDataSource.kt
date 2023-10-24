package com.abkhrr.gazego.core.database.source

import com.abkhrr.gazego.core.database.dao.MovieDetailsDao
import com.abkhrr.gazego.core.database.model.MovieDetailsEntity
import com.abkhrr.gazego.core.database.utils.GazegoDatabaseTransactionProvider
import javax.inject.Inject

class MovieDetailsDbDataSource @Inject constructor(
    private val movieDetailsDao: MovieDetailsDao,
    private val transactionProvider: GazegoDatabaseTransactionProvider
) {
    fun getById(id: Int) = movieDetailsDao.getById(id)
    fun getByIds(ids: List<Int>) = movieDetailsDao.getByIds(ids)

    suspend fun deleteAndInsert(movieDetails: MovieDetailsEntity) =
        transactionProvider.runWithTransaction {
            movieDetailsDao.deleteById(id = movieDetails.id)
            movieDetailsDao.insert(movieDetails)
        }

    suspend fun deleteAndInsertAll(movieDetailsList: List<MovieDetailsEntity>) =
        transactionProvider.runWithTransaction {
            movieDetailsList.forEach { movieDetails ->
                movieDetailsDao.deleteById(id = movieDetails.id)
                movieDetailsDao.insert(movieDetails)
            }
        }
}