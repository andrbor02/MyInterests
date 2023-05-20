package com.example.feature_streams.impl.domain.repository

import com.example.feature_streams.impl.domain.model.StreamInfo
import com.example.feature_streams.impl.domain.model.StreamModel
import com.example.feature_streams.impl.domain.model.TopicModel
import kotlinx.coroutines.flow.Flow

internal interface StreamsRepository {
    fun getStreams(): Flow<List<StreamModel>>

    fun getSubscribedStreams(): Flow<List<StreamModel>>

    suspend fun searchStreams(query: String): List<StreamModel>

    suspend fun getTopics(streamId: Int): List<TopicModel>

    suspend fun updateStreams(): Boolean

    suspend fun updateTopics(streamId: Int): Boolean

    suspend fun createStream(streamInfo: StreamInfo): Boolean
}