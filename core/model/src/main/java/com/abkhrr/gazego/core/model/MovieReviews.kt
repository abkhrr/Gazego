package com.abkhrr.gazego.core.model

data class MovieReviews(
    val page: Int,
    val results: List<Review>
)