package com.abkhrr.gazego.core.data.mapper

import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.network.model.NetworkMovieCategory

internal fun MovieCategory.Categories.asNetworkCategory() =
    NetworkMovieCategory.Categories[category]

internal fun MovieCategory.Common.asNetworkCategory() =
    NetworkMovieCategory.Common[category]