package com.example.feature_streams.impl.data.repository_impl

import com.example.core_data.impl.account.AccountPersister
import com.example.core_utils.common_helpers.isNotNull
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.feature_streams.impl.data.datasource.local.StreamsLocalDataSource
import com.example.feature_streams.impl.data.datasource.remote.StreamsRemoteDataSource
import com.example.feature_streams.impl.data.datasource.remote.model.streams.created.CreatedStreamResponse
import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicsResponse
import com.example.feature_streams.impl.data.utils.StreamLocalToDomainMapper
import com.example.feature_streams.impl.data.utils.StreamRemoteToLocalMapper
import com.example.feature_streams.impl.data.utils.TopicRemoteToDomainMapper
import com.example.feature_streams.impl.data.utils.TopicRemoteToLocalMapper
import com.example.feature_streams.impl.domain.model.StreamInfo
import com.example.feature_streams.impl.domain.model.StreamModel
import com.example.feature_streams.impl.domain.model.TopicModel
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class StreamsRepositoryImpl @Inject constructor(
    private val streamsRemoteDataSource: StreamsRemoteDataSource,
    private val streamsLocalDataSource: StreamsLocalDataSource,
    private val streamsRemoteToLocalMapper: StreamRemoteToLocalMapper,
    private val streamsLocalToDomainMapper: StreamLocalToDomainMapper,
    private val topicRemoteToLocalMapper: TopicRemoteToLocalMapper,
    private val topicRemoteToDomainMapper: TopicRemoteToDomainMapper,
    private val accountPersister: AccountPersister,
) : StreamsRepository {
    override fun getStreams(): Flow<List<StreamModel>> =
        streamsLocalDataSource.getAllStreams().map { streamsLocal ->
            streamsLocal.map { streamLocal ->
                streamsLocalToDomainMapper(streamLocal)
            }
        }.flowOn(Dispatchers.Default)

    override fun getSubscribedStreams(): Flow<List<StreamModel>> =
        streamsLocalDataSource.getSubscribedStreams().map { streamsLocal ->
            streamsLocal.map { streamLocal ->
                streamsLocalToDomainMapper(streamLocal)
            }
        }.flowOn(Dispatchers.Default)

    override suspend fun searchStreams(query: String): List<StreamModel> {
        return streamsLocalDataSource.searchStreams(query).map { streamLocal ->
            withContext(Dispatchers.Default) {
                streamsLocalToDomainMapper(streamLocal)
            }
        }
    }

    override suspend fun updateStreams(): Boolean {
        val updateSuccessful = runCatchingNonCancellation {
            coroutineScope {
                val subscribedIds = async(Dispatchers.Default) {
                    streamsRemoteDataSource.getSubscribedStreamsIds().subscriptions.map { it.id }
                }

                val allStreams = async(Dispatchers.Default) {
                    streamsRemoteDataSource.getStreams().streams.map { streamRemote ->
                        if (streamRemote.id in subscribedIds.await()) {
                            streamsRemoteToLocalMapper(streamRemote, true)
                        } else {
                            streamsRemoteToLocalMapper(streamRemote, false)
                        }
                    }
                }.await()

                streamsLocalDataSource.deleteAll()
                streamsLocalDataSource.insertStreams(allStreams)
            }
        }

        updateSuccessful.fold(
            onSuccess = { return true },
            onFailure = { return false }
        )
    }

    override suspend fun updateTopics(streamId: Int): Boolean {
        val updateSuccessful = runCatchingNonCancellation {
            val topicsLocal =
                streamsRemoteDataSource.getTopics(streamId).map { topics: TopicsResponse ->
                    topics.topics.map { topic ->
                        topicRemoteToLocalMapper(topic)
                    }
                }.first()

            val topicsSerialized = Json.encodeToString(topicsLocal)
            streamsLocalDataSource.updateStream(streamId) { streamLocal ->
                streamLocal.copy(topics = topicsSerialized)
            }
        }

        return updateSuccessful.fold(
            onSuccess = { return true },
            onFailure = { return false }
        )
    }

    override suspend fun getTopics(streamId: Int): List<TopicModel> {
        val topicsRemote =
            streamsRemoteDataSource.getTopics(streamId).map { topics: TopicsResponse ->
                topics.topics
            }.first()

        return topicsRemote.map { topicRemote ->
            topicRemoteToDomainMapper(topicRemote)
        }
    }

    override suspend fun createStream(streamInfo: StreamInfo): Boolean {
        val myEmail = accountPersister.getUser().email

        val createSuccessful = runCatchingNonCancellation {
            streamsRemoteDataSource.createStream(streamInfo)
                .map { createdStream: CreatedStreamResponse ->
                    val isSubscribed = createdStream.subscribed[myEmail].isNotNull
                    val isAlreadySubscribed = createdStream.alreadySubscribed[myEmail].isNotNull

                    if (isSubscribed) {
                        true
                    } else if (isAlreadySubscribed) {
                        false
                    } else {
                        throw Exception("Can't create channel")
                    }
                }.first()
        }

        return createSuccessful.fold(
            onSuccess = { isSuccessful ->
                updateStreams()
                return isSuccessful
            },
            onFailure = { return false }
        )
    }
}