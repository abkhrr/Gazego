package com.abkhrr.gazego.core.model

sealed interface MovieCategory {
    enum class Categories(val category: String) : MovieCategory {
        Upcoming(UpcomingCategoryType),
        TopRated(TopRatedCategoryType),
        Popular(PopularCategoryType),
        NowPlaying(NowPlayingCategoryType),
        Discover(DiscoverCategoryType),
        Trending(TrendingCategoryType);

        companion object {
            private val categories = entries.associateBy(Categories::category)
            operator fun get(category: String) = checkNotNull(categories[category]) {
                "Invalid media category: $category"
            }
        }
    }

    enum class Common(val category: String) : MovieCategory {
        Upcoming(UpcomingCategoryType),
        TopRated(TopRatedCategoryType),
        Popular(PopularCategoryType),
        NowPlaying(NowPlayingCategoryType),
        Discover(DiscoverCategoryType),
        Trending(TrendingCategoryType);

        companion object {
            private val categories = entries.associateBy(Common::category)
            operator fun get(category: String) = checkNotNull(categories[category]) {
                "Invalid media category: $category"
            }
        }
    }

    sealed class Details(val mediaId: Int, val mediaType: String) : MovieCategory {
        data class Movie(val id: Int) : Details(mediaId = id, mediaType = "movie")

        companion object {
            fun from(id: Int, category: String) = when (category) {
                "movie" -> Movie(id = id)
                else -> error("Invalid media category $category")
            }
        }
    }

    sealed class Reviews(val movieId: Int) : MovieCategory {
        data class MovieReviews(val id: Int) : Reviews(movieId = id)

        companion object {
            fun from(id: Int) = MovieReviews(id = id)
        }
    }

    enum class WishList { Movie }
}

private const val UpcomingCategoryType = "upcoming"
private const val TopRatedCategoryType = "top_rated"
private const val PopularCategoryType = "popular"
private const val NowPlayingCategoryType = "now_playing"
private const val DiscoverCategoryType = "discover"
private const val TrendingCategoryType = "trending"

