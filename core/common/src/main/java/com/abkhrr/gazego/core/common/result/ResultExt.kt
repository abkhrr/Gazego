package com.abkhrr.gazego.core.common.result

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <T> ApiResult<T>.isLoading(): Boolean {
    contract {
        returns() implies (this@isLoading is ApiResult.Loading<T>)
    }
    return this is ApiResult.Loading<T>
}

@OptIn(ExperimentalContracts::class)
fun <T> ApiResult<T>.isSuccess(): Boolean {
    contract {
        returns() implies (this@isSuccess is ApiResult.Success<T>)
    }
    return this is ApiResult.Success<T>
}

@OptIn(ExperimentalContracts::class)
fun <T> ApiResult<T>.isFailure(): Boolean {
    contract {
        returns() implies (this@isFailure is ApiResult.Failure<T>)
    }
    return this is ApiResult.Failure<T>
}