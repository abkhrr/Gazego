package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant
import com.abkhrr.gazego.core.model.MovieCategory

@Entity(tableName = Constant.Tables.WISHLIST)
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.Fields.ID)
    val id: Int = 0,

    @ColumnInfo(Constant.Fields.REMOTE_ID)
    val remoteId: Int,
)