package com.example.feature_streams.impl.data.datasource.remote.model.streams.created

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreatedStreamResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("subscribed")
    val subscribed: EmailAndSubscribedStreams,

    @SerialName("already_subscribed")
    val alreadySubscribed: EmailAndSubscribedStreams,
)

internal typealias EmailAndSubscribedStreams = HashMap<String, List<String>>