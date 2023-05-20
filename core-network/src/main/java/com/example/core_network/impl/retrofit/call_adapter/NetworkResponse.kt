package com.example.core_network.impl.retrofit.call_adapter

import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    data class UnknownError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()

    fun handle() = when (this) {
        is Success -> body
        is ApiError -> {
            val errorMessage = "$code: $body"
            throw Exception(errorMessage)
        }

        is NetworkError -> throw IOException(NO_INTERNET)
        is UnknownError -> throw error
    }

    companion object {
        private const val NO_INTERNET = "You have no internet connection"
    }
}