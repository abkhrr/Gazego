package com.abkhrr.gazego.core.designsystem.components

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.unit.dp

/**
 * Get is device width < 600.dp (Compact Device Width)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isCompactDevice: Boolean
    get() = maxWidth < 600.dp

/**
 * Get is device width between minimal value equal to 600.dp and max 839.dp (Medium Device Width)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isMediumDevice: Boolean
    get() = maxWidth in 600.dp..839.dp

/**
 * Get is device width >= 840.dp (Expanded Device Width)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isExpandedDevice: Boolean
    get() = maxWidth >= 840.dp

/**
 * Get is device height >= 840.dp (Expanded Device Height)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isCompactDeviceHeight: Boolean
    get() = maxHeight < 480.dp

/**
 * Get is device height between minimal value equal to 480.dp and max 899.dp (Medium Device Height)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isMediumDeviceHeight: Boolean
    get() = maxHeight in 480.dp..899.dp

/**
 * Get is device height >= 840.dp (Expanded Device Height)
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 */
val BoxWithConstraintsScope.isExpandedDeviceHeight: Boolean
    get() = maxHeight >= 900.dp