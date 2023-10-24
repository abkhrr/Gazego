package com.abkhrr.gazego.core.model

import kotlinx.datetime.LocalDate

data class Review(
    val id: String,
    val author: String,
    val authorDetails: ReviewAuthor,
    val content: String,
    val createdAt: LocalDate
)