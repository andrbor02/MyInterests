package com.example.feature_streams.impl.data.datasource.remote.model.topics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TopicRemote(
    @SerialName("name")
    val name: String,
)
