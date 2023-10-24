package com.abkhrr.gazego.features.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun HorizontalGenres(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    GenreItemsContentContainer(
        modifier = modifier,
        items = genres,
        itemContent = { genre -> GenreItem(genre) }
    )
}

@Composable
fun GenreItemsContentContainer(
    items: List<String>,
    itemContent: @Composable LazyItemScope.(String) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.smallMedium),
            contentPadding = PaddingValues(horizontal = GazegoTheme.spacing.smallMedium),
            userScrollEnabled = false
        ) {
            items(items = items, itemContent = itemContent)
        }
    }
}

@Composable
fun GenreItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(GazegoTheme.colors.surfaceColor)
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            text = text,
            style = GazegoTheme.typography.regular.h5,
            color = GazegoTheme.colors.white
        )
    }
}