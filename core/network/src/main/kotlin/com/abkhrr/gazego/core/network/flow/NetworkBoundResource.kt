package com.abkhrr.gazego.core.network.flow

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.common.result.isFailure
import com.abkhrr.gazego.core.common.result.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> ApiResult<RequestType>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
): Flow<ApiResult<ResultType>> = flow {
    emit(ApiResult.loading())
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(ApiResult.loading(data))
        val response = fetch()

        when {
            response.isSuccess() -> {
                saveFetchResult(response.value)
                query().map { ApiResult.success(it) }
            }
            response.isFailure() -> {
                val throwable = response.error
                query().map { ApiResult.failure(throwable, it) }
            }
            else -> error("Error: $response")
        }
    } else {
        query().map { ApiResult.success(it) }
    }

    emitAll(flow)
}