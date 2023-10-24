package com.abkhrr.gazego.core.database.model

import kotlinx.serialization.Serializable

@Serializable
data class SerializedReviewAuthor(
    val name: String,
    val username: String,
    val avatarPath: String? = "",
    val rating: Double? = 0.0
)