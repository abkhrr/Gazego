package com.abkhrr.gazego.core.common.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

suspend fun getBitmapFromUrl(src: String?): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(src)
            val inputStream = url.openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            return@withContext bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext null
        }
    }
}