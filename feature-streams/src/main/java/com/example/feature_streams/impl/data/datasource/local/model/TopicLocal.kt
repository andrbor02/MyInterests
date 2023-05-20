package com.example.feature_streams.impl.data.datasource.local.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TopicLocal(
    val name: String,
    val messagesCount: Int,
)