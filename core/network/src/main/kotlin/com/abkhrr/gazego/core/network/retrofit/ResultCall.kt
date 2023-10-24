package com.abkhrr.gazego.core.network.retrofit

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.common.result.HttpException
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, ApiResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<ApiResult<T>>) =
        proxy.enqueue(ResultCallback(this, callback))

    override fun cloneImpl(): ResultCall<T> = ResultCall(proxy.clone())

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<ApiResult<T>>
    ) : Callback<T> {

        @Suppress("UNCHECKED_CAST")
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val request = (call.request() as Request)
            val result = if (response.isSuccessful) {
                ApiResult.success(
                    value = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    url = request.url.toString()
                )
            } else {
                ApiResult.failure(
                    HttpException(
                        statusCode = response.code(),
                        statusMessage = response.message(),
                        url = request.url.toString()
                    )
                )
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is retrofit2.HttpException -> ApiResult.failure<T>(
                    HttpException(
                        statusCode = error.code(),
                        statusMessage = error.message(),
                        cause = error
                    )
                )
                is IOException -> ApiResult.failure(error)
                else -> ApiResult.failure(error)
            }

            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout = proxy.timeout()
}