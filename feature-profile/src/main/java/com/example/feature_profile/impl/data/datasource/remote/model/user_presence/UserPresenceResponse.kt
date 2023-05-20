package com.example.feature_profile.impl.data.datasource.remote.model.user_presence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserPresenceResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("presence")
    val presence: PlatformWithPresence,
)

internal typealias PlatformWithPresence = HashMap<String, Presence>