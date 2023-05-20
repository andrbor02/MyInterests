package com.example.feature_streams.impl.data.datasource.remote.model.streams.all

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StreamRemote(
    @SerialName("stream_id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,
)