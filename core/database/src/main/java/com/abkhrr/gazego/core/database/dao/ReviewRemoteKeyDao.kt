package com.abkhrr.gazego.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.ReviewRemoteKeysEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.REVIEW_REMOTE_KEYS

@Dao
interface ReviewRemoteKeyDao {
    @Query("SELECT * FROM $REVIEW_REMOTE_KEYS WHERE id = :id AND movie_id = :movieId")
    suspend fun getByIdAndMovieId(id: Int, movieId: Int): ReviewRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<ReviewRemoteKeysEntity>)

    @Query("DELETE FROM $REVIEW_REMOTE_KEYS WHERE movie_id = :movieId")
    suspend fun deleteByMovieId(movieId: Int)
}