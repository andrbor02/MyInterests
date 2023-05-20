package com.example.feature_chat.impl.data.datasource.local

import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal

internal interface MessagesLocalDataSource {

    suspend fun getMessagesFromStream(streamId: Int): List<MessageLocal>

    suspend fun getMessagesFromTopic(streamId: Int, topicName: String): List<MessageLocal>

    suspend fun getMessage(messageId: Int): MessageLocal

    suspend fun insertMessagesToStream(list: List<MessageLocal>, streamId: Int)

    suspend fun insertMessage(message: MessageLocal)

    suspend fun deleteAll()
}