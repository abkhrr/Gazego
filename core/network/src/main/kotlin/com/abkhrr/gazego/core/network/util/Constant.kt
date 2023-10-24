package com.abkhrr.gazego.core.network.util

internal object Constant {
    internal const val PAGE_SIZE = 30
    internal const val DEFAULT_PAGE = 1
    internal const val DEFAULT_LANG = "en-US"

    internal object Endpoint {
        internal const val TOP_RATED_MOVIE = "/3/movie/top_rated"
        internal const val POPULAR_MOVIE = "/3/movie/popular"
        internal const val NOW_PLAYING_MOVIE = "/3/movie/now_playing"
        internal const val DISCOVER_MOVIE = "/3/discover/movie"
        internal const val TRENDING_MOVIE = "/3/trending/movie/day"
        internal const val DETAILS_MOVIE = "/3/movie/{movie_id}"
        internal const val SEARCH_MOVIE = "/3/search/movie"
        internal const val MOVIE_REVIEWS = "/3/movie/{movie_id}/reviews"
        internal const val DISCOVER_UPCOMING = "/3/discover/movie"
        internal const val VIDEO = "/3/movie/{movie_id}/videos"
    }

    internal object Query {
        internal const val PAGE = "page"
        internal const val QUERY = "query"
        internal const val APPEND_TO_RESPONSE = "append_to_response"

        internal const val INCLUDE_ADULT = "include_adult"
        internal const val INCLUDE_VIDEO = "include_video"
        internal const val LANGUAGE = "language"
        internal const val PRIMARY_RELEASE_YEAR = "primary_release_year"
        internal const val PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte"
        internal const val PRIMARY_RELEASE_DATE_LTE = "primary_release_date.lte"
        internal const val SORT_BY = "sort_by"

        internal val DETAILS_APPEND_TO_RESPONSE = buildAppendToResponse("reviews")

        // Build append to response
        // https://developer.themoviedb.org/docs/append-to-response
        private fun buildAppendToResponse(vararg fields: String) =
            fields.joinToString(separator = APPEND_TO_RESPONSE_SEPARATOR)

        private const val APPEND_TO_RESPONSE_SEPARATOR = ","
    }

    internal object Path {
        internal const val MOVIE_ID = "movie_id"
    }

    internal object SerialName {
        internal const val ID = "id"
        internal const val PAGE = "page"
        internal const val TOTAL_PAGES = "total_pages"
        internal const val RESULTS = "results"
        internal const val TOTAL_RESULTS = "total_results"
        internal const val DATES = "dates"
        internal const val MAXIMUM = "maximum"
        internal const val MINIMUM = "minimum"
    }
}

const val PAGE_SIZE = Constant.PAGE_SIZE
const val DEFAULT_PAGE = Constant.DEFAULT_PAGE