package com.abkhrr.gazego.core.domain.mapper

import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.model.Genre
import com.abkhrr.gazego.core.model.MovieDetails
import kotlinx.datetime.LocalDate

fun MovieDetails.asTestModel() = MovieDetails(
    id = 1,
    title = "Title",
    overview = "Lorem Ipsum",
    adult = false,
    imdbId = "123",
    originalLanguage = "en",
    originalTitle = "Title",
    backdropPath = "/backdrop",
    posterPath = "/poster",
    genres = listOf(Genre(id = 1, name = "Action"), Genre(id = 2, name = "Sci-fy")),
    releaseDate = LocalDate(2023, 10, 23),
    runtime = 143,
    video = false,
    voteAverage = 6.4,
    voteCount = 2000,
    tagline = emptyString(),
    isOnWishlist = false
)