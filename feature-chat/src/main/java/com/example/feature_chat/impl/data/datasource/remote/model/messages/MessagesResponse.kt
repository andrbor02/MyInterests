package com.example.feature_chat.impl.data.datasource.remote.model.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MessagesResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("messages")
    val messages: List<MessageRemote>,

    @SerialName("found_newest")
    val foundNewest: Boolean,
)