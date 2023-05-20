package com.example.feature_people.impl.data.datasource.remote.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserFromPeopleListRemote(
    @SerialName("user_id")
    val id: Int,

    @SerialName("avatar_url")
    val avatar: String?,

    @SerialName("is_active")
    val isActive: Boolean,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("email")
    val email: String,
)