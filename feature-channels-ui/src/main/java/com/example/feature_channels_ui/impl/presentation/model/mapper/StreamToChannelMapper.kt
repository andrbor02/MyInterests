package com.example.feature_channels_ui.impl.presentation.model.mapper

import com.example.feature_channels_ui.impl.presentation.model.ChannelModel
import com.example.feature_streams.impl.domain.model.StreamModel
import javax.inject.Inject

internal interface StreamToChannelMapper {
    operator fun invoke(streamModel: StreamModel, expandedStreams: List<Int>): ChannelModel
}

internal class StreamToChannelMapperImpl @Inject constructor() : StreamToChannelMapper {
    override operator fun invoke(
        streamModel: StreamModel,
        expandedStreams: List<Int>,
    ): ChannelModel {
        return ChannelModel(
            id = streamModel.id,
            name = streamModel.name,
            isExpanded = expandedStreams.contains(streamModel.id),
            topics = streamModel.topics
        )
    }
}