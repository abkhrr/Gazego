package com.abkhrr.gazego.core.network.di

import com.abkhrr.gazego.core.network.service.MovieService
import com.abkhrr.gazego.core.network.service.ReviewService
import com.abkhrr.gazego.core.network.service.VideoService
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
    fun providesMovieService(retrofit: Retrofit) : MovieService = retrofit.create(
        MovieService::class.java
    )

    @Singleton
    @Provides
    fun providesReviewService(retrofit: Retrofit) : ReviewService = retrofit.create(
        ReviewService::class.java
    )

    @Singleton
    @Provides
    fun providesVideoService(retrofit: Retrofit) : VideoService = retrofit.create(
        VideoService::class.java
    )
}