package com.abkhrr.gazego.features.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import kotlinx.datetime.LocalDate

class MovieResourcesPreview: PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> = sequenceOf(homeUiState)
}

val category = MovieCategory.Categories.Upcoming

val homeUiState: HomeUiState
    get() = HomeUiState(
        movies = homeMovies,
        loadStates = mapOf(category to false)
    )

val homeMovies = mapOf(
    MovieCategory.Categories.Upcoming to listOf(
        Movie(
            id = 299054,
            title = "Expend4bles",
            overview = "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
            releaseDate = LocalDate.parse("2023-09-15"),
            genreIds = listOf(28, 12, 53),
            voteAverage = 6.3,
            popularity = 2085.194,
            posterPath = "https://image.tmdb.org/t/p/w780/mOX5O6JjCUWtlYp5D8wajuQRVgy.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w780/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg"
        ),
        Movie(
            id = 299054,
            title = "Expend4bles",
            overview = "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
            releaseDate = LocalDate.parse("2023-09-15"),
            genreIds = listOf(28, 12, 53),
            voteAverage = 6.3,
            popularity = 2085.194,
            posterPath = "https://image.tmdb.org/t/p/w780/mOX5O6JjCUWtlYp5D8wajuQRVgy.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w780/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg"
        ),
        Movie(
            id = 299054,
            title = "Expend4bles",
            overview = "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
            releaseDate = LocalDate.parse("2023-09-15"),
            genreIds = listOf(28, 12, 53),
            voteAverage = 6.3,
            popularity = 2085.194,
            posterPath = "https://image.tmdb.org/t/p/w780/mOX5O6JjCUWtlYp5D8wajuQRVgy.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w780/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg"
        ),
        Movie(
            id = 299054,
            title = "Expend4bles",
            overview = "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
            releaseDate = LocalDate.parse("2023-09-15"),
            genreIds = listOf(28, 12, 53),
            voteAverage = 6.3,
            popularity = 2085.194,
            posterPath = "https://image.tmdb.org/t/p/w780/mOX5O6JjCUWtlYp5D8wajuQRVgy.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w780/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg"
        ),
        Movie(
            id = 299054,
            title = "Expend4bles",
            overview = "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
            releaseDate = LocalDate.parse("2023-09-15"),
            genreIds = listOf(28, 12, 53),
            voteAverage = 6.3,
            popularity = 2085.194,
            posterPath = "https://image.tmdb.org/t/p/w780/mOX5O6JjCUWtlYp5D8wajuQRVgy.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w780/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg"
        )
    )
)