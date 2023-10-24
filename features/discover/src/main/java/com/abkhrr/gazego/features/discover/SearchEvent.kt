package com.abkhrr.gazego.features.discover

sealed interface SearchEvent {
    data class ChangeQuery(val value: String) : SearchEvent

    data object Refresh : SearchEvent
    data object Retry : SearchEvent
    data object ClearError : SearchEvent
}