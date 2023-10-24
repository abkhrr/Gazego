package com.abkhrr.gazego.di

import com.abkhrr.gazego.GazeGoApplication
import com.abkhrr.gazego.core.network.WebApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideWebApiProvider(
        app: GazeGoApplication
    ) : WebApiProvider = WebApiProvider(app.applicationContext)

    @Singleton
    @Provides
    fun provideRetrofit(
        webApiProvider: WebApiProvider,
        app: GazeGoApplication
    ): Retrofit = webApiProvider.getRetrofit(app.getBaseUrl())
}