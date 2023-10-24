package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant

@Entity(Constant.Tables.VIDEO)
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("remoteId")
    val remoteId: String,

    @ColumnInfo("movie_id")
    val movieId: Int,

    @ColumnInfo("iso_639_1")
    val iso6391: String,

    @ColumnInfo("iso_3166_1")
    val iso31661: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("key")
    val key: String,

    @ColumnInfo("site")
    val site: String,

    @ColumnInfo("size")
    val size: Int,

    @ColumnInfo("type")
    val type: String,

    @ColumnInfo("official")
    val official: Boolean
)