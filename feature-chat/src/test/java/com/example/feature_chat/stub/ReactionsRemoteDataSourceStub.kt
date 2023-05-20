package com.example.feature_chat.stub

import com.example.feature_chat.impl.data.datasource.remote.ReactionsRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionsResponse

internal class ReactionsRemoteDataSourceStub : ReactionsRemoteDataSource {
    override suspend fun addReaction(messageId: Int, reactionName: String): ReactionsResponse {
        return createReactionsResponse()
    }

    override suspend fun removeReaction(messageId: Int, reactionName: String): ReactionsResponse {
        TODO("Not yet implemented")
    }

    private fun createReactionsResponse() =
        ReactionsResponse(
            "success",
            ""
        )
}