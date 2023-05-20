package com.example.feature_streams.impl.data.datasource.local.impl

import com.example.feature_streams.impl.data.datasource.local.StreamsLocalDataSource
import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal
import com.example.feature_streams.impl.data.datasource.local.room.StreamsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class StreamsLocalDataSourceImpl @Inject constructor(
    private val streamsDao: StreamsDao,
) : StreamsLocalDataSource {
    override fun getAllStreams(): Flow<List<StreamLocal>> {
        return streamsDao.getAllStreams()
    }

    override fun getSubscribedStreams(): Flow<List<StreamLocal>> {
        return streamsDao.getSubscribedStreams()
    }

    override suspend fun updateStream(streamId: Int, transform: (StreamLocal) -> StreamLocal) {
        val oldStream = streamsDao.getStream(streamId)
        val newStream = transform(oldStream)
        streamsDao.insert(newStream)
    }

    override suspend fun searchStreams(query: String): List<StreamLocal> {
        return streamsDao.search(query)
    }

    override suspend fun insertStreams(list: List<StreamLocal>) {
        streamsDao.insertList(list)
    }

    override suspend fun deleteAll() {
        streamsDao.deleteAll()
    }
}