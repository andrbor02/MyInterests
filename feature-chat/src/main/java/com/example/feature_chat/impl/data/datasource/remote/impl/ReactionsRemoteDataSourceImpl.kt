package com.example.feature_chat.impl.data.datasource.remote.impl

import com.example.feature_chat.impl.data.datasource.remote.ReactionsRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.api.ReactionsApi
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionsResponse
import javax.inject.Inject

internal class ReactionsRemoteDataSourceImpl @Inject constructor(
    private val reactionsApi: ReactionsApi,
) : ReactionsRemoteDataSource {
    override suspend fun addReaction(messageId: Int, reactionName: String): ReactionsResponse {
        return reactionsApi.addReaction(messageId, reactionName).handle()
    }

    override suspend fun removeReaction(messageId: Int, reactionName: String): ReactionsResponse {
        return reactionsApi.removeReaction(messageId, reactionName).handle()
    }
}