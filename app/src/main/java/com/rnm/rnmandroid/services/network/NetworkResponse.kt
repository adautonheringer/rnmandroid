package com.rnm.rnmandroid.services.network

import java.lang.Exception

sealed class NetworkResponse<T>(
    data: T? = null,
    exception: Exception? = null,
) {
    data class Success<T>(val data: T) : NetworkResponse<T>(
        data = data,
        exception = null
    )
    data class Error<T>(val exception: Exception) : NetworkResponse<T>(
        data = null,
        exception = exception
    )
}
