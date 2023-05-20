package com.example.feature_streams.impl.data.utils

import com.example.feature_streams.impl.data.datasource.local.model.TopicLocal
import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicRemote
import javax.inject.Inject


internal interface TopicRemoteToLocalMapper {
    operator fun invoke(topic: TopicRemote): TopicLocal
}

internal class TopicRemoteToLocalMapperImpl @Inject constructor() : TopicRemoteToLocalMapper {
    override operator fun invoke(topic: TopicRemote): TopicLocal {
        return TopicLocal(
            topic.name,
            -1,
        )
    }
}