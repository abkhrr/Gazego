package com.abkhrr.gazego.core.network.response

import com.abkhrr.gazego.core.network.model.NetworkReview
import com.abkhrr.gazego.core.network.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName(Constant.SerialName.ID)
    val id: Int,

    @SerialName(Constant.SerialName.PAGE)
    val page: Int,

    @SerialName(Constant.SerialName.RESULTS)
    val results: List<NetworkReview>
)