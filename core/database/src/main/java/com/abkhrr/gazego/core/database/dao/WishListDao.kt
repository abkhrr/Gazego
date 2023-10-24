package com.abkhrr.gazego.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abkhrr.gazego.core.database.model.WishlistEntity
import com.abkhrr.gazego.core.database.utils.Constant.Tables.WISHLIST
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListDao {
    @Query("SELECT * FROM $WISHLIST")
    fun getAll(): Flow<List<WishlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wishlist: WishlistEntity)

    @Query("DELETE FROM $WISHLIST WHERE remote_id = :id")
    suspend fun deleteByRemoteId(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM $WISHLIST WHERE remote_id = :id)")
    suspend fun isWishlisted(id: Int): Boolean
}