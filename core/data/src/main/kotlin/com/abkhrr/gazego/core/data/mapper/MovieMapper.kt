package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.database.model.MovieDetailsEntity
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.model.MovieDetails
import com.abkhrr.gazego.core.network.model.NetworkMovie
import com.abkhrr.gazego.core.network.model.NetworkMovieDetail

internal fun MovieEntity.asModel() = Movie(
    id = remoteId,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterPath = posterPath,
    backdropPath = backdropPath,
    genreIds = genreIds,
    popularity = popularity
)

internal fun NetworkMovie.asEntity(category: MovieCategory.Categories) = MovieEntity(
    remoteId = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterPath = posterPath,
    backdropPath = backdropPath,
    genreIds = genreIds,
    popularity = popularity,
    category = category
)

internal fun NetworkMovie.asModel() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterPath = posterPath,
    backdropPath = backdropPath,
    genreIds = genreIds,
    popularity = popularity
)

// === movie detail ===

internal fun NetworkMovieDetail.asEntity() = MovieDetailsEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genres = genres?.map { it.asSerialized() },
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    runtime = runtime,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

internal fun MovieDetailsEntity.asModel(isOnWishlist: Boolean) = MovieDetails(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genres = genres?.map { it.asModel() },
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    runtime = runtime,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isOnWishlist = isOnWishlist
)