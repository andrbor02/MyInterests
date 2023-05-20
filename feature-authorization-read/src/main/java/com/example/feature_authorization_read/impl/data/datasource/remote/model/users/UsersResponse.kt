package com.example.feature_people.impl.data.datasource.remote.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UsersResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("members")
    val members: List<UserFromPeopleListRemote>,
)
