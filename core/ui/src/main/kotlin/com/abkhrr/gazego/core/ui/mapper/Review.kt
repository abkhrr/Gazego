package com.abkhrr.gazego.core.ui.mapper

import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.model.ReviewAuthor
import kotlinx.datetime.LocalDate

fun Review.asReview() = Review(
    id = id,
    author = author,
    authorDetails = authorDetails.asReviewAuthor(),
    content = content,
    createdAt = createdAt
)

fun ReviewAuthor.asReviewAuthor() = ReviewAuthor(
    username = username,
    name = name,
    avatarPath = avatarPath,
    rating = rating
)