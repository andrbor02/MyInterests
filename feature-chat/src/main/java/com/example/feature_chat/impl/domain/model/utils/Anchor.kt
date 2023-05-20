package com.example.feature_chat.impl.domain.model.utils

sealed class Anchor {
    data class MessageId(val id: Int) : Anchor()
    data class Special(val value: String) : Anchor()
}