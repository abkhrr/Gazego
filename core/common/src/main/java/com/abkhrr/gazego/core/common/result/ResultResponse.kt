package com.abkhrr.gazego.core.common.result

internal interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
    val url: String?
}

class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val url: String? = null,
    cause: Throwable? = null
) : Exception(null, cause) {
    override fun toString(): String {
        return "$statusCode $statusMessage $url $cause"
    }
}