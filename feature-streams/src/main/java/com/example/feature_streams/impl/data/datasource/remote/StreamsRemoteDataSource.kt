package com.example.feature_streams.impl.data.datasource.remote


import com.example.feature_streams.impl.data.datasource.remote.model.streams.all.StreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.created.CreatedStreamResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.subscribed.SubscribedStreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicsResponse
import com.example.feature_streams.impl.domain.model.StreamInfo
import kotlinx.coroutines.flow.Flow

internal interface StreamsRemoteDataSource {

    suspend fun getStreams(includeSubscribed: Boolean = false): StreamsResponse

    suspend fun getSubscribedStreamsIds(): SubscribedStreamsResponse

    suspend fun getTopics(streamId: Int): Flow<TopicsResponse>

    suspend fun createStream(streamInfo: StreamInfo): Flow<CreatedStreamResponse>
}