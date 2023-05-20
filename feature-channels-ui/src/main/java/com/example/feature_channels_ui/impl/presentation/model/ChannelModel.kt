package com.example.feature_channels_ui.impl.presentation.model

import com.example.feature_streams.impl.domain.model.TopicModel

internal data class ChannelModel(
    val id: Int,
    val name: String,
    val isExpanded: Boolean,
    val topics: List<TopicModel>,
)
