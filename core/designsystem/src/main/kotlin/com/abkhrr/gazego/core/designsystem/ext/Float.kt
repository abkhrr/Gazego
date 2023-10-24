package com.abkhrr.gazego.core.designsystem.ext

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import kotlin.math.abs

fun Float.angularDistanceTo(beta: Float): Float {
    val phi = (abs(beta - this) % 360).toInt()
    val distance = if (phi > 180) 360 - phi else phi
    return distance.toFloat()
}

fun FloatArray.hue() = getOrNull(0)

fun FloatArray.toColor() = Color(ColorUtils.HSLToColor(this))

fun FloatArray.withValues(
    hue: Float? = null,
    saturation: Float? = null,
    lightness: Float? = null
) = floatArrayOf(
    hue ?: get(0),
    saturation ?: get(1),
    lightness ?: get(2)
)