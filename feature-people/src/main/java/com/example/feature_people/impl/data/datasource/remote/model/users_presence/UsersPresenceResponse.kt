package com.example.feature_people.impl.data.datasource.remote.model.users_presence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UsersPresenceResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("presences")
    val presences: UserWithPlatform,
)

internal typealias UserWithPlatform = HashMap<String, PlatformWithPresence>

internal typealias PlatformWithPresence = HashMap<String, PresenceWithClientRemote>