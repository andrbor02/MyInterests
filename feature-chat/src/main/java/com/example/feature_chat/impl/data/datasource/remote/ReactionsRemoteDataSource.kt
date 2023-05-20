package com.example.feature_chat.impl.data.datasource.remote

import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionsResponse

internal interface ReactionsRemoteDataSource {

    suspend fun addReaction(messageId: Int, reactionName: String): ReactionsResponse

    suspend fun removeReaction(messageId: Int, reactionName: String): ReactionsResponse
}