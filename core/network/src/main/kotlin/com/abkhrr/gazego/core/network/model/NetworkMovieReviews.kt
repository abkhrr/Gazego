package com.abkhrr.gazego.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieReviews(
    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val results: List<NetworkReview>
)