package com.abkhrr.gazego.core.network

import android.content.Context
import com.abkhrr.gazego.core.network.retrofit.ResultAdapterFactory
import com.abkhrr.gazego.core.network.util.defaultJson
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WebApiProvider @Inject constructor(@ApplicationContext val app: Context) {
    fun getRetrofit(url: String, json: Json = defaultJson): Retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .client(buildRetrofitClient())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(ResultAdapterFactory())
        .build()

    private fun buildRetrofitClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(getChuckInterceptor())
            builder.addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().build()
                chain.proceed(newRequest)
            }
        }

        builder.addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request
                .newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxODFhODFkOTExZGU3ODY0MDIyNWE0ZTExZjliZDgzNyIsInN1YiI6IjVmZjM3MzViMzIyYjJiMDAzY2EyYzRmOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xpPpdd3tcEUAT8clYx9AkdHwn1mEcQJJpDFlWm9iggs")
                .build()

            chain.proceed(newRequest)
        }
        return builder.build()
    }

    private fun getChuckInterceptor(): ChuckerInterceptor {
        val chuckCollector = ChuckerCollector(context = app, showNotification = true)
        return ChuckerInterceptor.Builder(context = app)
            .collector(chuckCollector)
            .alwaysReadResponseBody(true)
            .build()
    }
}