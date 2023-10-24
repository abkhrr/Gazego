package com.abkhrr.gazego

import com.abkhrr.gazego.core.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GazeGoApplication: BaseApplication() {
    override fun getBaseUrl(): String = BuildConfig.BASE_URL
}