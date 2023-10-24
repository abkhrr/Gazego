package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant
import com.abkhrr.gazego.core.model.MovieCategory

@Entity(tableName = Constant.Tables.MOVIES_REMOTE_KEYS)
data class MovieRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Constant.Fields.ID)
    val id: Int,

    @ColumnInfo(name = Constant.Fields.CATEGORY)
    val category: MovieCategory.Categories,

    @ColumnInfo(name = Constant.Fields.PREV)
    val prevPage: Int?,

    @ColumnInfo(name = Constant.Fields.NEXT)
    val nextPage: Int?
)