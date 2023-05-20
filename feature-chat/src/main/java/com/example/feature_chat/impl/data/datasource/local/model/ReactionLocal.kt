package com.example.feature_chat.impl.data.datasource.local.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ReactionLocal(
    val name: String,
    val code: Int,
    val count: Int,
    val isClicked: Boolean,
)
