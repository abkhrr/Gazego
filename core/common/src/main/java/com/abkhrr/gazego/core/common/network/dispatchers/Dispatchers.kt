package com.abkhrr.gazego.core.common.network.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val gazegoDispatcher: GazegoDispatchers)

enum class GazegoDispatchers {
    Default,
    IO,
    Main
}