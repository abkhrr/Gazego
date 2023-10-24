package com.abkhrr.gazego.core.database.converter

import androidx.room.TypeConverter
import com.abkhrr.gazego.core.database.model.SerializedReviewAuthor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class ReviewAuthorConverter {
    @TypeConverter
    internal fun creditsToString(reviewAuthor: SerializedReviewAuthor): String = Json.encodeToString(reviewAuthor)

    @TypeConverter
    internal fun stringToCredits(string: String): SerializedReviewAuthor = Json.decodeFromString(string)
}