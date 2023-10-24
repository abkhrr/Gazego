package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant
import kotlinx.datetime.LocalDate

@Entity(Constant.Tables.REVIEW)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(Constant.Fields.ID)
    val id: Int = 0,

    @ColumnInfo(Constant.Fields.MOVIE_ID)
    val movieId: Int,

    @ColumnInfo(Constant.Fields.REMOTE_ID)
    val remoteId: String,

    @ColumnInfo(Constant.Fields.AUTHOR)
    val author: String,

    @ColumnInfo(Constant.Fields.AUTHOR_DETAILS)
    val authorDetails: SerializedReviewAuthor,

    @ColumnInfo(Constant.Fields.CONTENT)
    val content: String,

    @ColumnInfo(Constant.Fields.CREATED_AT)
    val createdAt: LocalDate
)