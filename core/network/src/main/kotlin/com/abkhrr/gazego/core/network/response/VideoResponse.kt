package com.abkhrr.gazego.core.network.response

import com.abkhrr.gazego.core.network.model.NetworkVideo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("results")
    val results: List<NetworkVideo>? = emptyList()
)