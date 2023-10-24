package com.abkhrr.gazego.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abkhrr.gazego.core.common.ext.emptyString

@Composable
fun HorizontalMovieItemPlaceholder(
    modifier: Modifier = Modifier
) {
    HorizontalFeedItemPlaceholder(modifier = modifier)
}

@Composable
internal fun HorizontalFeedItemPlaceholder(modifier: Modifier = Modifier) {
    HorizontalFeedItem(
        modifier = modifier,
        title = emptyString(),
        posterPath = emptyString(),
        voteAverage = 0.0,
        onClick = {},
        isPlaceholder = true
    )
}
