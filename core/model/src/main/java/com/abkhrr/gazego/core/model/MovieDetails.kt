package com.abkhrr.gazego.core.model

import com.abkhrr.gazego.core.common.ext.emptyString
import kotlinx.datetime.LocalDate

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String? = emptyString(),
    val posterPath: String? = emptyString(),
    val genres: List<Genre>? = emptyList(),
    val releaseDate: LocalDate?,
    val runtime: Int,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val tagline: String,
    val isOnWishlist: Boolean
)