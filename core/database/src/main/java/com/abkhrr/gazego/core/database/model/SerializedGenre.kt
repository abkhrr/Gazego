package com.abkhrr.gazego.core.database.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerializedGenre(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)
