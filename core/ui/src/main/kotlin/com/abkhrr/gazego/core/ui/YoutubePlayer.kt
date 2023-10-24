package com.abkhrr.gazego.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.abkhrr.gazego.core.common.ext.findActivity
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(videoId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var player: YouTubePlayer? = null
    val playerFragment = YouTubePlayerView(context)

    val playerStateListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            player = youTubePlayer
            youTubePlayer.loadVideo(videoId, 0f)
        }
    }

    val playerBuilder = IFramePlayerOptions.Builder().apply {
        controls(1)
        fullscreen(0)
        autoplay(0)
        rel(1)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = modifier.background(GazegoTheme.colors.surfaceColor),
            factory = {
                playerFragment.apply {
                    enableAutomaticInitialization = false
                    initialize(playerStateListener, playerBuilder.build())
                }
            }
        )

        DisposableEffect(key1 = Unit, effect = {
            context.findActivity() ?: return@DisposableEffect onDispose {}
            onDispose {
                playerFragment.removeYouTubePlayerListener(playerStateListener)
                playerFragment.release()
                player = null
            }
        })

        DisposableEffect(lifecycleOwner) {
            val lifecycle = lifecycleOwner.lifecycle
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        player?.play()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        player?.pause()
                    }
                    else -> {
                        //
                    }
                }
            }
            lifecycle.addObserver(observer)
            onDispose {
                lifecycle.removeObserver(observer)
            }
        }
    }
}
