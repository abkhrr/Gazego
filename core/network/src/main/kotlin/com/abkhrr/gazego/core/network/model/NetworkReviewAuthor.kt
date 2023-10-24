package com.abkhrr.gazego.core.network.model

import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyInt
import com.abkhrr.gazego.core.common.ext.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReviewAuthor(
    @SerialName("name")
    val name: String,

    @SerialName("username")
    val username: String,

    @SerialName("avatar_path")
    val avatarPath: String? = emptyString(),

    @SerialName("rating")
    val rating: Double? = emptyDouble()
)