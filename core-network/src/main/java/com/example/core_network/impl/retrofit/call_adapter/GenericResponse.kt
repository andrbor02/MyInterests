package com.example.core_network.impl.retrofit.call_adapter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GenericResponse<T> = NetworkResponse<T, GenericApiError>

@Serializable
data class GenericApiError(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("code")
    val code: String,
)

