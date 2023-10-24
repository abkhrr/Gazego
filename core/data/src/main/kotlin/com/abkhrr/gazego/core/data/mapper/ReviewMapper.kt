package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.model.SerializedReviewAuthor
import com.abkhrr.gazego.core.model.Review
import com.abkhrr.gazego.core.model.ReviewAuthor
import com.abkhrr.gazego.core.network.model.NetworkReview
import com.abkhrr.gazego.core.network.model.NetworkReviewAuthor
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun NetworkReviewAuthor.asSerialized() = SerializedReviewAuthor(
    name, username, avatarPath, rating
)

internal fun NetworkReview.asEntity(movieId: Int) = ReviewEntity(
    remoteId = id,
    movieId = movieId,
    author = author,
    authorDetails = authorDetails.asSerialized(),
    content = content,
    createdAt = createdAt.toLocalDateTime(TimeZone.currentSystemDefault()).date
)

internal fun SerializedReviewAuthor.asModel() = ReviewAuthor(
    name, username, avatarPath, rating
)

internal fun ReviewEntity.asModel() = Review(
    id = remoteId,
    author = author,
    authorDetails = authorDetails.asModel(),
    content = content,
    createdAt = createdAt
)