package com.example.feature_streams.impl.data.datasource.local

import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal
import kotlinx.coroutines.flow.Flow

internal interface StreamsLocalDataSource {

    fun getAllStreams(): Flow<List<StreamLocal>>

    fun getSubscribedStreams(): Flow<List<StreamLocal>>

    suspend fun updateStream(streamId: Int, transform: (StreamLocal) -> StreamLocal)

    suspend fun searchStreams(query: String): List<StreamLocal>

    suspend fun insertStreams(list: List<StreamLocal>)

    suspend fun deleteAll()
}