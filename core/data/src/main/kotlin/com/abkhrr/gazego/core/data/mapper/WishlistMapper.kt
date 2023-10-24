package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.database.model.WishlistEntity
import com.abkhrr.gazego.core.model.WishList

internal fun WishlistEntity.asModel() = WishList(
    id = remoteId
)