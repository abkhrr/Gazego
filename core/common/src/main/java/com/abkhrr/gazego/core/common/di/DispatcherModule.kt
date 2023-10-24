package com.abkhrr.gazego.core.common.di

import com.abkhrr.gazego.core.common.network.dispatchers.Dispatcher
import com.abkhrr.gazego.core.common.network.dispatchers.GazegoDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @Dispatcher(GazegoDispatchers.Default)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(GazegoDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(GazegoDispatchers.Main)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}