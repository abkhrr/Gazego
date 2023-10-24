package com.abkhrr.gazego.core.database.dao

import androidx.paging.PagingSource
import com.abkhrr.gazego.core.database.utils.Constant.Tables.MOVIE
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM $MOVIE WHERE category_type = :category LIMIT :pageSize")
    fun getByCategory(category: MovieCategory.Categories, pageSize: Int): Flow<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE WHERE category_type = :category")
    fun getPagingByCategory(category: MovieCategory.Categories): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM $MOVIE WHERE category_type = :category")
    suspend fun deleteByCategory(category: MovieCategory.Categories)
}