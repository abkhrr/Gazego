package com.abkhrr.gazego.core.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.ui.R
import java.io.IOException

class Message(
    @StringRes val messageResourceId: Int,
    vararg val args: Any
)

@Composable
fun Message.asString() = stringResource(id = messageResourceId, formatArgs = args)

fun Throwable.asMessage() = Message(
    messageResourceId = when(this) {
        is IOException -> R.string.no_internet_connection
        else -> R.string.unknown_error
    }
)