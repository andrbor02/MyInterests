package com.example.feature_streams.impl.data.utils

import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal
import com.example.feature_streams.impl.domain.model.StreamModel
import com.example.feature_streams.impl.domain.model.TopicModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal interface StreamLocalToDomainMapper {
    operator fun invoke(streamLocal: StreamLocal): StreamModel
}

internal class StreamLocalToDomainMapperImpl @Inject constructor() : StreamLocalToDomainMapper {
    override fun invoke(streamLocal: StreamLocal): StreamModel {
        val topics = try {
            Json.decodeFromString<List<TopicModel>>(streamLocal.topics)
        } catch (t: Throwable) {
            emptyList()
        }

        return StreamModel(
            id = streamLocal.id,
            name = streamLocal.name,
            topics = topics
        )
    }
}