package com.abkhrr.gazego.core.ui.handler

interface EventHandler<E> {
    fun onEvent(event: E)
}