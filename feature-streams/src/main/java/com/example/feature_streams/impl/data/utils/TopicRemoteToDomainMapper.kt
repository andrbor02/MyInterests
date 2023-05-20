package com.example.feature_streams.impl.data.utils

import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicRemote
import com.example.feature_streams.impl.domain.model.TopicModel
import javax.inject.Inject

internal interface TopicRemoteToDomainMapper {
    operator fun invoke(topic: TopicRemote): TopicModel
}

internal class TopicRemoteToDomainMapperImpl @Inject constructor() : TopicRemoteToDomainMapper {
    override operator fun invoke(topic: TopicRemote): TopicModel {
        return TopicModel(
            topic.name,
            -1,
        )
    }
}