package com.abkhrr.gazego.core.model

import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyString

data class ReviewAuthor(
    val name: String,
    val username: String,
    val avatarPath: String? = emptyString(),
    val rating: Double? = emptyDouble()
)