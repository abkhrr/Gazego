package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.database.model.SerializedGenre
import com.abkhrr.gazego.core.model.Genre
import com.abkhrr.gazego.core.network.model.NetworkGenre

internal fun NetworkGenre.asSerialized() = SerializedGenre(
    id = id,
    name = name
)

internal fun SerializedGenre.asModel() = Genre(
    id = id,
    name = name
)