package com.abkhrr.gazego.core.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.abkhrr.gazego.core.designsystem.components.LocalSnackbarHostState
import com.abkhrr.gazego.core.ui.model.Message
import com.abkhrr.gazego.core.ui.model.asString

@Composable
fun SnackbarMessageHandler(
    messages: Message?,
    onShowMessage: (String) -> Unit,
    onDismiss: () -> Unit,
    snackbarHostState: SnackbarHostState = LocalSnackbarHostState.current
) {
    if (messages == null) return
    val message = messages.asString()
    LaunchedEffect(snackbarHostState, messages) {
        onShowMessage(message)
        onDismiss()
    }
}