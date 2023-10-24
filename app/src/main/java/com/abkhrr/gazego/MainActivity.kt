package com.abkhrr.gazego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.abkhrr.gazego.ui.GazegoApp
import com.abkhrr.gazego.ui.GazegoAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            val darkTheme = isSystemInDarkTheme()

            DisposableEffect(darkTheme) {
                // Setting as always dark -> In this case
                // AppCompatDelegate.setDefaultNightMode() Will Not Working
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.Transparent.toArgb(),
                        Color.Transparent.toArgb(),
                    ){ darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = darkScrim,
                        darkScrim = darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }
            GazegoApp()
        }
    }
}

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)