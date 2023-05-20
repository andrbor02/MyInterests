package com.example.feature_streams.impl.data.datasource.remote.model.streams.subscribed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SubscribedStreamsResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("subscriptions")
    val subscriptions: List<SubscribedStreamIdRemote>,
)