package com.example.feature_chat.impl.data.datasource.remote.model.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MessageResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val msg: String,

    @SerialName("message")
    val message: MessageRemote,
)