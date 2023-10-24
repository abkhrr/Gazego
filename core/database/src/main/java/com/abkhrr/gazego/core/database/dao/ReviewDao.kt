package com.abkhrr.gazego.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.REVIEW
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM $REVIEW WHERE movie_id = :movieId LIMIT :pageSize")
    fun getByMovie(movieId: Int, pageSize: Int): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM $REVIEW WHERE movie_id = :movieId")
    fun getPagingByMovieId(movieId: Int): PagingSource<Int, ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: ReviewEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(review: List<ReviewEntity>)

    @Query("DELETE FROM $REVIEW WHERE movie_id = :movieId")
    suspend fun deleteByMovie(movieId: Int)
}