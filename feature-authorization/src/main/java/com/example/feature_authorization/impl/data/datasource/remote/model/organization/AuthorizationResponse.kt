package com.example.feature_authorization.impl.data.datasource.remote.model.organization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthorizationResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("authentication_methods")
    val authenticationMethods: HashMap<String, Boolean>,

    @SerialName("email_auth_enabled")
    val emailAuthEnabled: Boolean,

    @SerialName("is_incompatible")
    val isIncompatible: Boolean,
)