package com.abkhrr.gazego.core.common.base

import android.app.Application

abstract class BaseApplication: Application() {
    abstract fun getBaseUrl(): String
}