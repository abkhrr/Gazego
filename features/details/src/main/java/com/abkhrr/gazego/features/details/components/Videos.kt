package com.abkhrr.gazego.features.details.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.designsystem.components.GazegoMessage
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.ui.VideoPlayer
import com.abkhrr.gazego.core.ui.YoutubePlayer
import com.abkhrr.gazego.features.details.R

@Composable
fun TrailerPlayer(
    modifier: Modifier = Modifier,
    video: Video? = null,
) {
    Column(
        modifier = modifier.padding(horizontal = GazegoTheme.spacing.extraMedium).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GazegoTheme.spacing.small)
    ) {
        Text(
            text = stringResource(id = R.string.video),
            style = GazegoTheme.typography.semiBold.h4,
            color = GazegoTheme.colors.white
        )

        if (video == null) {
            GazegoMessage(
                modifier = Modifier.fillMaxWidth(),
                messageResourceId = R.string.noVideos,
                imageResourceId = R.drawable.no_videos
            )
        } else {
            YoutubePlayer(video.key)
        }
    }
}