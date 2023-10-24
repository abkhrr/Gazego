package com.abkhrr.gazego.core.domain.repository

import com.abkhrr.gazego.core.model.WishList
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    fun getAll(): Flow<List<WishList>>

    suspend fun insert(id: Int)
    suspend fun remove(id: Int)
}