package com.abkhrr.gazego.core.database.source

import com.abkhrr.gazego.core.database.dao.VideoDao
import com.abkhrr.gazego.core.database.model.VideoEntity
import com.abkhrr.gazego.core.database.utils.GazegoDatabaseTransactionProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoDbDataSource @Inject constructor(
    private val videoDao: VideoDao,
    private val transactionProvider: GazegoDatabaseTransactionProvider
) {
    fun getByMovieId(movieId: Int): Flow<List<VideoEntity>> =
        videoDao.getByMovieId(movieId)

    suspend fun deleteAndInsert(
        movieId: Int,
        video: VideoEntity
    ) {
        transactionProvider.runWithTransaction {
            videoDao.deleteByMovieId(movieId)
            videoDao.insert(video)
        }
    }

    suspend fun deleteAndInsertAll(
        movieId: Int,
        videos: List<VideoEntity>
    ) {
        transactionProvider.runWithTransaction {
            videoDao.deleteByMovieId(movieId)
            videos.forEach {
                videoDao.insert(it)
            }
        }
    }
}