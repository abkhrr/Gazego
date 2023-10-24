package com.abkhrr.gazego.features.home.components

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.common.utils.getBitmapFromUrl
import com.abkhrr.gazego.core.designsystem.components.GazegoConstrainedBox
import com.abkhrr.gazego.core.designsystem.components.GazegoImage
import com.abkhrr.gazego.core.designsystem.components.GazegoImagePlaceholder
import com.abkhrr.gazego.core.designsystem.components.isCompactDevice
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.ui.MoviesContainer
import com.abkhrr.gazego.core.ui.utils.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun UpcomingMovieContainer(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val movieList = when {
        movies.isEmpty() -> emptyList()
        movies.size <= 5 -> movies
        else -> movies.take(5)
    }

    val shouldShowPlaceholder = movieList.isEmpty()
    val pageCount = if (shouldShowPlaceholder) {
        PlaceholderCount
    } else {
        movieList.size
    }

    val pagerState = rememberPagerState(pageCount = { pageCount })

    GazegoConstrainedBox(modifier = modifier) {
        var isCompactDeviceWidth by remember { mutableStateOf(isCompactDevice) }

        val configuration = LocalConfiguration.current
        LaunchedEffect(configuration) {
            snapshotFlow {
                configuration.orientation
            }.collect {
                isCompactDeviceWidth = configuration.screenWidthDp < 600
            }
        }

        MoviesContainer(modifier = modifier) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = GazegoTheme.spacing.large),
            ) { page ->
                if (shouldShowPlaceholder) {
                    UpcomingMovieItemPlaceholder(
                        modifier = Modifier.pagerTransition(pagerState, page)
                    )
                } else {
                    with(movieList[page]) {
                        var urlPath by remember { mutableStateOf(posterPath) }
                        var imageHeightDp by remember { mutableStateOf(420.dp) }
                        var imagePath by remember { mutableStateOf(posterPath) }

                        urlPath = when {
                            isCompactDeviceWidth -> Constants.PosterImagePath.WIDTH_MEDIUM
                            else -> Constants.BackdropImagePath.WIDTH_LARGE
                        }

                        imageHeightDp = when {
                            isCompactDeviceWidth -> 420.dp
                            else -> 154.dp
                        }

                        imagePath = when {
                            isCompactDeviceWidth -> posterPath
                            else -> backdropPath
                        }

                        UpcomingMovieItem(
                            modifier = Modifier.pagerTransition(pagerState, page),
                            title = title,
                            imagePath = "${urlPath}${imagePath}",
                            imageHeight = imageHeightDp,
                            onClick = { onMovieClick(id) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(GazegoTheme.spacing.medium))

            DefaultHorizontalPagerIndicator(
                modifier = Modifier
                    .padding(horizontal = GazegoTheme.spacing.extraLarge)
                    .align(Alignment.CenterHorizontally),
                activeIndicatorWidth = GazegoTheme.spacing.large,
                pagerState = pagerState,
                pageCount = pageCount
            )
        }
    }
}

@Composable
private fun UpcomingMovieItem(
    title: String,
    imagePath: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 420.dp,
    shape: Shape = GazegoTheme.shapes.medium,
    isPlaceholder: Boolean = false
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var palette by remember { mutableStateOf<Palette?>(null) }

    LaunchedEffect(imagePath) {
        if (!isPlaceholder) {
            val loadedBitmap = getBitmapFromUrl(imagePath)
            bitmap = loadedBitmap
            palette = bitmap?.let { Palette.from(it).generate() }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(imageHeight)
            .clip(shape)
            .then(if (isPlaceholder) Modifier else Modifier.clickable(onClick = onClick)),
        shape = shape,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isPlaceholder) {
                GazegoImagePlaceholder()
            } else {
                GazegoImage(
                    modifier = Modifier.fillMaxSize(),
                    model = imagePath,
                    contentDescription = title,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun UpcomingMovieItemPlaceholder(modifier: Modifier = Modifier) {
    UpcomingMovieItem(
        modifier = modifier,
        title = emptyString(),
        imagePath = emptyString(),
        onClick = {},
        isPlaceholder = true
    )
}

private const val PlaceholderCount = 20