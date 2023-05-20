package com.example.feature_chat.impl.domain.model

data class SendMessageModel(
    val stream: String,
    val topic: String,
    val text: String,
)
