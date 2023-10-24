package com.abkhrr.gazego.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.abkhrr.gazego.core.database.model.MovieDetailsEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.MOVIE_DETAILS
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM $MOVIE_DETAILS WHERE id = :id")
    fun getById(id: Int): Flow<MovieDetailsEntity?>

    @Query("SELECT * FROM $MOVIE_DETAILS WHERE id IN (:ids)")
    fun getByIds(ids: List<Int>): Flow<List<MovieDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetails: MovieDetailsEntity)

    @Query("DELETE FROM $MOVIE_DETAILS WHERE id = :id")
    suspend fun deleteById(id: Int)
}