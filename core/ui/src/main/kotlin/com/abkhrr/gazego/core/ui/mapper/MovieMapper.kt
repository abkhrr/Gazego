package com.abkhrr.gazego.core.ui.mapper

import com.abkhrr.gazego.core.model.Movie

fun Movie.asMovie() = Movie(
    id, title, overview, releaseDate, genreIds, voteAverage, popularity, posterPath, backdropPath
)