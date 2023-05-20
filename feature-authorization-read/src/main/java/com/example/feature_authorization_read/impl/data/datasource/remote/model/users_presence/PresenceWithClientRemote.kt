package com.example.feature_people.impl.data.datasource.remote.model.users_presence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PresenceWithClientRemote(
    @SerialName("client")
    val client: String,

    @SerialName("status")
    val status: String,

    @SerialName("timestamp")
    val timestamp: Int,
)
