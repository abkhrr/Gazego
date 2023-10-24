package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.datetime.LocalDate

@Entity(tableName = Constant.Tables.MOVIE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.Fields.ID)
    val id: Int = 0,

    @ColumnInfo(name = Constant.Fields.REMOTE_ID)
    val remoteId: Int,

    @ColumnInfo(name = Constant.Fields.TITLE)
    val title: String,

    @ColumnInfo(name = Constant.Fields.OVERVIEW)
    val overview: String,

    @ColumnInfo(name = Constant.Fields.POPULARITY)
    val popularity: Double,

    @ColumnInfo(name = Constant.Fields.RELEASE_DATE)
    val releaseDate: LocalDate?,

    @ColumnInfo(name = Constant.Fields.GENRE_IDS)
    val genreIds: List<Int>,

    @ColumnInfo(name = Constant.Fields.VOTE_AVERAGE)
    val voteAverage: Double,

    @ColumnInfo(name = Constant.Fields.POSTER_PATH)
    val posterPath: String?,

    @ColumnInfo(name = Constant.Fields.BACKDROP_PATH)
    val backdropPath: String?,

    @ColumnInfo(name = Constant.Fields.CATEGORY)
    val category: MovieCategory.Categories
)