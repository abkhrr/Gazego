package com.abkhrr.gazego.core.designsystem.ext

import androidx.palette.graphics.Palette

fun Palette.themeSwatchSelection(): List<Palette.Swatch>? {
    val primary = dominantSwatch ?: return null

    var secondaryAngularDistance = 0f
    var secondary = primary

    swatches.forEach { secondaryCandidate ->
        if (primary.hsl.hue() != secondaryCandidate.hsl.hue()) {
            val primaryHue = primary.hsl.hue() ?: 0f
            val angularDistance = primaryHue.angularDistanceTo(
                beta = secondaryCandidate.hsl.hue() ?: 0f
            )

            if (angularDistance > secondaryAngularDistance) {
                secondaryAngularDistance = angularDistance
                secondary = secondaryCandidate
            }
        }
    }

    return listOf(primary, secondary)
}