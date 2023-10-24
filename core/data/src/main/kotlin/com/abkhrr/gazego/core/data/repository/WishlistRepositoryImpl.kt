package com.abkhrr.gazego.core.data.repository

import com.abkhrr.gazego.core.data.mapper.asModel
import com.abkhrr.gazego.core.data.utils.listMap
import com.abkhrr.gazego.core.database.model.WishlistEntity
import com.abkhrr.gazego.core.database.source.WishlistDbDataSource
import com.abkhrr.gazego.core.domain.repository.WishListRepository
import com.abkhrr.gazego.core.model.WishList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val databaseDataSource: WishlistDbDataSource
) : WishListRepository {
    override fun getAll(): Flow<List<WishList>> =
        databaseDataSource.getAll().listMap(WishlistEntity::asModel)

    override suspend fun insert(id: Int) =
        databaseDataSource.addToWishlist(id)

    override suspend fun remove(id: Int) =
        databaseDataSource.removeFromWishlist(id)
}