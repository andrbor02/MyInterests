package com.example.feature_profile.impl.data.datasource.remote.model.user_presence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Presence(
    @SerialName("status")
    val status: String,

    @SerialName("timestamp")
    val timestamp: Int,
)
