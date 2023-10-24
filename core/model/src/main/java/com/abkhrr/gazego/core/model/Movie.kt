package com.abkhrr.gazego.core.model

import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyInt
import com.abkhrr.gazego.core.common.ext.emptyString
import kotlinx.datetime.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: LocalDate?,
    val genreIds: List<Int>,
    val voteAverage: Double,
    val popularity: Double,
    val posterPath: String?,
    val backdropPath: String?
)