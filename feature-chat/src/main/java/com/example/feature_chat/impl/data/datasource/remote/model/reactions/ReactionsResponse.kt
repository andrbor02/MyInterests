package com.example.feature_chat.impl.data.datasource.remote.model.reactions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ReactionsResponse(

    @SerialName("result")
    val result: String,

    @SerialName("msg")
    val message: String,
)