package com.example.feature_streams.impl.data.datasource.remote.impl


import com.example.core_utils_android.serialization.serializeListOfMaps
import com.example.feature_streams.impl.data.datasource.remote.StreamsRemoteDataSource
import com.example.feature_streams.impl.data.datasource.remote.api.StreamApi
import com.example.feature_streams.impl.data.datasource.remote.model.streams.all.StreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.created.CreatedStreamResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.subscribed.SubscribedStreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicsResponse
import com.example.feature_streams.impl.domain.model.StreamInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class StreamsRemoteDataSourceImpl @Inject constructor(
    private val streamApi: StreamApi,

    ) : StreamsRemoteDataSource {
    override suspend fun getStreams(includeSubscribed: Boolean): StreamsResponse {
        return streamApi.getStreams().handle()
    }

    override suspend fun getSubscribedStreamsIds(): SubscribedStreamsResponse {
        return streamApi.getSubscribedStreams().handle()
    }

    override suspend fun getTopics(streamId: Int): Flow<TopicsResponse> = flowOf(
        streamApi.getTopics(streamId).handle()
    )

    override suspend fun createStream(streamInfo: StreamInfo): Flow<CreatedStreamResponse> {
        val subscriptions = serializeListOfMaps(
            listOf(
                hashMapOf(
                    NAME to streamInfo.name,
                    DESCRIPTION to streamInfo.description,
                )
            )
        )
        return flowOf(
            streamApi.createStream(
                subscriptions,
                isPublic = streamInfo.hasPublicHistory
            ).handle()
        )
    }

    companion object {
        private const val NAME = "name"
        private const val DESCRIPTION = "description"
    }
}