package com.abkhrr.gazego.core.network.model

sealed interface NetworkMovieCategory {
    enum class Categories(val category: String) : NetworkMovieCategory {
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

    enum class Common(val category: String) : NetworkMovieCategory {
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
}

private const val UpcomingCategoryType = "upcoming"
private const val TopRatedCategoryType = "top_rated"
private const val PopularCategoryType = "popular"
private const val NowPlayingCategoryType = "now_playing"
private const val DiscoverCategoryType = "discover"
private const val TrendingCategoryType = "trending"

