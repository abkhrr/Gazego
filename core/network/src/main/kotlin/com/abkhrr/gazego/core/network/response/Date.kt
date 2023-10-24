package com.abkhrr.gazego.core.network.response

import com.abkhrr.gazego.core.network.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDates(
    @SerialName(Constant.SerialName.MAXIMUM)
    val maximum: String,

    @SerialName(Constant.SerialName.MINIMUM)
    val minimum: String
)