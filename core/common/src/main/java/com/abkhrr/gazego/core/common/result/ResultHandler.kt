package com.abkhrr.gazego.core.common.result

import kotlinx.coroutines.flow.Flow

interface ApiResultHandler<T> {
    fun onLoading(block: (T?) -> Unit)
    fun onSuccess(block: (T) -> Unit)
    fun onFailure(block: (Throwable) -> Unit)
}

fun <T> ApiResult<T>.handle(builder: ApiResultHandler<T>.() -> Unit) {
    val resultHandler = object : ApiResultHandler<T> {
        override fun onLoading(block: (T?) -> Unit) {
            if (isLoading()) block(value)
        }

        override fun onSuccess(block: (T) -> Unit) {
            if (isSuccess()) block(value)
        }

        override fun onFailure(block: (Throwable) -> Unit) {
            if (isFailure()) block(error)
        }
    }

    builder(resultHandler)
}

suspend fun <T> Flow<ApiResult<T>>.handle(builder: ApiResultHandler<T>.() -> Unit) =
    collect { result -> result.handle(builder = builder) }