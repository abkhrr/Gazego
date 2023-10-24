package com.abkhrr.gazego.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.VideoEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.VIDEO
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM $VIDEO WHERE movie_id = :movieId")
    fun getByMovieId(movieId: Int): Flow<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetails: VideoEntity)

    @Query("DELETE FROM $VIDEO WHERE movie_id = :movieId")
    suspend fun deleteByMovieId(movieId: Int)
}