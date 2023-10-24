package com.abkhrr.gazego.core.ui.mapper

import com.abkhrr.gazego.core.model.MovieDetails

fun MovieDetails.asMovieDetails() = MovieDetails(
    id,
    title,
    overview,
    adult,
    imdbId,
    originalLanguage,
    originalTitle,
    backdropPath,
    posterPath,
    genres,
    releaseDate,
    runtime,
    video,
    voteAverage,
    voteCount,
    tagline,
    isOnWishlist
)