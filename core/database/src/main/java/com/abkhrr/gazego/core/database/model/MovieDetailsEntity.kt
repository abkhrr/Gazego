package com.abkhrr.gazego.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abkhrr.gazego.core.database.utils.Constant
import com.abkhrr.gazego.core.model.Genre
import kotlinx.datetime.LocalDate

@Entity(tableName = Constant.Tables.MOVIE_DETAILS)
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Constant.Fields.ID)
    val id: Int,

    @ColumnInfo(name = Constant.Fields.ADULT)
    val adult: Boolean,

    @ColumnInfo(name = Constant.Fields.TITLE)
    val title: String,

    @ColumnInfo(name = Constant.Fields.OVERVIEW)
    val overview: String,

    @ColumnInfo(name = Constant.Fields.IMDB_ID)
    val imdbId: String?,

    @ColumnInfo(name = Constant.Fields.ORIGINAL_LANGUAGE)
    val originalLanguage: String,

    @ColumnInfo(name = Constant.Fields.ORIGINAL_TITLE)
    val originalTitle: String,

    @ColumnInfo(name = Constant.Fields.BACKDROP_PATH)
    val backdropPath: String?,

    @ColumnInfo(name = Constant.Fields.POSTER_PATH)
    val posterPath: String?,

    @ColumnInfo(name = Constant.Fields.GENRES)
    val genres: List<SerializedGenre>? = emptyList(),

    @ColumnInfo(name = Constant.Fields.RELEASE_DATE)
    val releaseDate: LocalDate?,

    @ColumnInfo(name = Constant.Fields.RUNTIME)
    val runtime: Int,

    @ColumnInfo(name = Constant.Fields.VIDEO)
    val video: Boolean,

    @ColumnInfo(name = Constant.Fields.VOTE_AVERAGE)
    val voteAverage: Double,

    @ColumnInfo(name = Constant.Fields.VOTE_COUNT)
    val voteCount: Int,

    @ColumnInfo(name = Constant.Fields.TAGLINE)
    val tagline: String,
)