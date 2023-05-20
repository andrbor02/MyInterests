package com.example.feature_profile.impl.data.datasource.remote.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Profile(
    @SerialName("user_id")
    val id: Int,

    @SerialName("avatar_url")
    val avatar: String?,

    @SerialName("full_name")
    val fullName: String,
)
