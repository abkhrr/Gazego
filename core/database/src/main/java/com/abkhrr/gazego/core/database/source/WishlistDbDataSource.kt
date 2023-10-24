package com.abkhrr.gazego.core.database.source

import com.abkhrr.gazego.core.database.dao.WishListDao
import com.abkhrr.gazego.core.database.model.WishlistEntity
import javax.inject.Inject

class WishlistDbDataSource @Inject constructor(private val wishlistDao: WishListDao) {
    fun getAll() = wishlistDao.getAll()

    suspend fun addToWishlist(id: Int) =
        wishlistDao.insert(WishlistEntity(remoteId = id))

    suspend fun removeFromWishlist(id: Int) =
        wishlistDao.deleteByRemoteId(id)

    suspend fun isWishlisted(id: Int) = wishlistDao.isWishlisted(id)
}