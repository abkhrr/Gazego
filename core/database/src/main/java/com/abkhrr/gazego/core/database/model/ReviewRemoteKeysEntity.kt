package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant

@Entity(tableName = Constant.Tables.REVIEW_REMOTE_KEYS)
data class ReviewRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Constant.Fields.ID)
    val id: Int = 0,

    @ColumnInfo(name = Constant.Fields.MOVIE_ID)
    val movieId: Int,

    @ColumnInfo(name = Constant.Fields.PREV)
    val prevPage: Int?,

    @ColumnInfo(name = Constant.Fields.NEXT)
    val nextPage: Int?
)