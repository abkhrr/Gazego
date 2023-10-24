package com.abkhrr.gazego.core.network.model

import com.abkhrr.gazego.core.common.ext.emptyString
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NetworkMovieDetail(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("imdb_id")
    val imdbId: String? = emptyString(),

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("backdrop_path")
    val backdropPath: String? = emptyString(),

    @SerialName("poster_path")
    val posterPath: String? = emptyString(),

    @SerialName("genres")
    val genres: List<NetworkGenre>? = emptyList(),

    @SerialName("release_date")
    val releaseDate: LocalDate?,

    @SerialName("runtime")
    val runtime: Int,

    @SerialName("video")
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("tagline")
    val tagline: String,
)