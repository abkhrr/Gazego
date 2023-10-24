package com.abkhrr.gazego.core.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReview(
    @SerialName("id")
    val id: String,

    @SerialName("author")
    val author: String,

    @SerialName("author_details")
    val authorDetails: NetworkReviewAuthor,

    @SerialName("content")
    val content: String,

    @SerialName("created_at")
    val createdAt: Instant
)