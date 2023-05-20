package com.example.feature_chat.impl.domain.model

import com.example.feature_chat.impl.domain.model.reactions.ReactionModel

data class MessageModel(
    val id: Int,
    val senderId: Int,
    val avatar: String?,
    val name: String,
    val time: String,
    val topic: String,
    val text: String,
    val date: String,
    val timestamp: Int,
    val reactions: List<ReactionModel>,
)