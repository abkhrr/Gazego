package com.abkhrr.gazego.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.MovieRemoteKeyEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.MOVIES_REMOTE_KEYS
import com.abkhrr.gazego.core.model.MovieCategory

@Dao
interface MoviesRemoteKeysDao {
    @Query("SELECT * FROM $MOVIES_REMOTE_KEYS WHERE id = :id AND category_type = :category")
    suspend fun getByIdAndCategory(id: Int, category: MovieCategory.Categories): MovieRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKeyEntity>)

    @Query("DELETE FROM $MOVIES_REMOTE_KEYS WHERE category_type = :category")
    suspend fun deleteByCategory(category: MovieCategory.Categories)
}