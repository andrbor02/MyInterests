package com.example.feature_chat.impl.data.datasource.remote.model.reactions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ReactionRemote(
    @SerialName("emoji_code")
    val emojiCode: String,

    @SerialName("emoji_name")
    val emojiName: String,

    @SerialName("user_id")
    val userId: Int,
)