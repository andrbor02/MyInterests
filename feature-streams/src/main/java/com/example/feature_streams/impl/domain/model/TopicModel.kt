package com.example.feature_streams.impl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TopicModel(
    val name: String,
    val messagesCount: Int,
)