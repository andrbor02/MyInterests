package com.example.feature_chat.stub

import com.example.feature_chat.impl.data.datasource.local.MessagesLocalDataSource
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal

internal class MessagesLocalDataSourceStub : MessagesLocalDataSource {
    override suspend fun getMessagesFromStream(streamId: Int): List<MessageLocal> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessagesFromTopic(
        streamId: Int,
        topicName: String,
    ): List<MessageLocal> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessage(messageId: Int): MessageLocal {
        TODO("Not yet implemented")
    }

    override suspend fun insertMessagesToStream(list: List<MessageLocal>, streamId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun insertMessage(message: MessageLocal) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}