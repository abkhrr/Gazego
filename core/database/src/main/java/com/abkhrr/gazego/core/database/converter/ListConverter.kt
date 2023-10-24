package com.abkhrr.gazego.core.database.converter

import androidx.room.TypeConverter
import com.abkhrr.gazego.core.database.model.SerializedGenre
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class ListConverter {
    @TypeConverter
    internal fun intListToString(list: List<Int>): String = Json.encodeToString(list)

    @TypeConverter
    internal fun stringToIntList(string: String): List<Int> = Json.decodeFromString(string)

    @TypeConverter
    internal fun stringListToString(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    internal fun stringToStringList(string: String): List<String> = Json.decodeFromString(string)

    @TypeConverter
    internal fun genreListToString(list: List<SerializedGenre>): String = Json.encodeToString(list)

    @TypeConverter
    internal fun stringToGenreList(string: String): List<SerializedGenre> = Json.decodeFromString(string)
}