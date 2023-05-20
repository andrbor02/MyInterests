package com.example.feature_people.impl.data.datasource.remote.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiKeyResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("api_key")
    val apiKey: String,

    @SerialName("user_id")
    val userId: Int,

    @SerialName("email")
    val email: String,
)