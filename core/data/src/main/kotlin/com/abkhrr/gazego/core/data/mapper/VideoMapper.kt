package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.database.model.VideoEntity
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.network.model.NetworkVideo

internal fun VideoEntity.asModel() = Video(
    id = remoteId,
    iso6391 = iso6391,
    iso31661 = iso31661,
    name = name,
    key = key,
    size = size,
    site = site,
    type = type,
    official = official
)

internal fun NetworkVideo.asEntity(movieId: Int) = VideoEntity(
    remoteId = id,
    movieId = movieId,
    iso6391 = iso6391,
    iso31661 = iso31661,
    name = name,
    key = key,
    size = size,
    site = site,
    type = type,
    official = official
)