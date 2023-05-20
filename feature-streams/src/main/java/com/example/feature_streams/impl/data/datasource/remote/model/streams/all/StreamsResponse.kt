package com.example.feature_streams.impl.data.datasource.remote.model.streams.all

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StreamsResponse(
    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,

    @SerialName("streams")
    val streams: List<StreamRemote>,
)