package com.abkhrr.gazego.features.details

sealed interface DetailsEvent {
    data object Wishlist : DetailsEvent
    data object Refresh : DetailsEvent
    data object Retry : DetailsEvent
    data object ClearError : DetailsEvent
    data object ClearUserMessage : DetailsEvent
}