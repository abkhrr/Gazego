package com.abkhrr.gazego.features.home

sealed interface HomeEvent {
    data object Refresh : HomeEvent
    data object Retry : HomeEvent
    data object ClearError : HomeEvent
}