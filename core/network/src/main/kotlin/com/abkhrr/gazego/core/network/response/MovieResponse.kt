package com.abkhrr.gazego.core.network.response

import com.abkhrr.gazego.core.network.model.NetworkMovie
import com.abkhrr.gazego.core.network.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieResponse(
    @SerialName(Constant.SerialName.PAGE)
    val page: Int,

    @SerialName(Constant.SerialName.RESULTS)
    val results: List<NetworkMovie>,

    @SerialName(Constant.SerialName.TOTAL_PAGES)
    val totalPages: Int,

    @SerialName(Constant.SerialName.TOTAL_RESULTS)
    val totalResults: Int,

    @SerialName(Constant.SerialName.DATES)
    val dates: NetworkDates?
)