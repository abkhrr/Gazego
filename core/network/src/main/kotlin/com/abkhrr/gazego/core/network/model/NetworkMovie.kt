package com.abkhrr.gazego.core.network.model

import com.abkhrr.gazego.core.common.ext.emptyString
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("release_date")
    val releaseDate: LocalDate?,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("poster_path")
    val posterPath: String? = emptyString(),

    @SerialName("backdrop_path")
    val backdropPath: String? = emptyString(),
)