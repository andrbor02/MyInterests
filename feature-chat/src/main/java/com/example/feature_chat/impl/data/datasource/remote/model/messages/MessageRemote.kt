package com.example.feature_chat.impl.data.datasource.remote.model.messages

import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MessageRemote(
    @SerialName("id")
    val id: Int,

    @SerialName("content")
    val content: String,

    @SerialName("avatar_url")
    val avatar: String?,

    @SerialName("reactions")
    val reactions: List<ReactionRemote>,

    @SerialName("subject")
    val topic: String,

    @SerialName("sender_id")
    val senderId: Int,

    @SerialName("timestamp")
    val timestamp: Int,

    @SerialName("sender_full_name")
    val senderFullName: String,
)
