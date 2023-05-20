package com.example.feature_streams.impl.data.datasource.remote.model.topics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TopicsResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("topics")
    val topics: List<TopicRemote>,
)