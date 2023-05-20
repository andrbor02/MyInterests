package com.example.feature_streams.impl.data.datasource.remote.model.streams.subscribed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SubscribedStreamIdRemote(
    @SerialName("stream_id")
    val id: Int,
)