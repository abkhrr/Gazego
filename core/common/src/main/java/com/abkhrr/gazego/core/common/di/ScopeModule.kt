package com.abkhrr.gazego.core.common.di

import com.abkhrr.gazego.core.common.network.scope.ApplicationScope
import com.abkhrr.gazego.core.common.network.dispatchers.Dispatcher
import com.abkhrr.gazego.core.common.network.dispatchers.GazegoDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScopeModule {
    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @Dispatcher(GazegoDispatchers.Default) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}