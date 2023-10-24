package com.abkhrr.gazego.di

import android.content.Context
import com.abkhrr.gazego.GazeGoApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApp(@ApplicationContext app: Context): GazeGoApplication {
        return app as GazeGoApplication
    }
}