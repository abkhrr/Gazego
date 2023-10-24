package com.abkhrr.gazego.core.database.utils

internal object Constant {
    internal object Tables {
        internal const val WISHLIST = "wishlist"
        internal const val MOVIE = "movie"
        internal const val MOVIE_DETAILS = "movie_details"
        internal const val REVIEW = "review"
        internal const val VIDEO = "videos"
        internal const val MOVIES_REMOTE_KEYS = "movies_remote_keys"
        internal const val REVIEW_REMOTE_KEYS = "review_remote_keys"
    }

    internal object Fields {
        internal const val ID = "id"
        internal const val IMDB_ID = "imdb_id"
        internal const val REMOTE_ID = "remote_id"
        internal const val CATEGORY = "category_type"
        internal const val NAME = "name"
        internal const val TITLE = "title"
        internal const val ADULT = "adult"
        internal const val OVERVIEW = "overview"
        internal const val POPULARITY = "popularity"
        internal const val RELEASE_DATE = "release_date"
        internal const val GENRES = "genres"
        internal const val GENRE_IDS = "genre_ids"
        internal const val VOTE_AVERAGE = "vote_average"
        internal const val POSTER_PATH = "poster_path"
        internal const val BACKDROP_PATH = "backdrop_path"
        internal const val ORIGINAL_LANGUAGE = "original_language"
        internal const val ORIGINAL_TITLE = "original_title"
        internal const val RUNTIME = "runtime"
        internal const val VIDEO = "video"
        internal const val VOTE_COUNT = "vote_count"
        internal const val TAGLINE = "tagline"
        internal const val CONTENT = "content"
        internal const val CREATED_AT = "created_at"
        internal const val AUTHOR = "author"
        internal const val AUTHOR_DETAILS = "author_details"
        internal const val RATING = "rating"
        internal const val USERNAME = "username"
        internal const val AVATAR_PATH = "avatar_path"
        internal const val MOVIE_ID = "movie_id"
        internal const val REVIEW_ID = "review_id"
        internal const val NEXT = "next_page"
        internal const val PREV = "prev_page"
    }
}