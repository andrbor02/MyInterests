package com.example.feature_chat.impl.data.datasource.local.impl

import com.example.feature_chat.impl.data.datasource.local.MessagesLocalDataSource
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal
import com.example.feature_chat.impl.data.datasource.local.room.MessagesDao
import javax.inject.Inject

internal class MessagesLocalDataSourceImpl @Inject constructor(
    private val messagesDao: MessagesDao,
) : MessagesLocalDataSource {
    override suspend fun getMessagesFromStream(streamId: Int): List<MessageLocal> {
        return messagesDao.getAllFromStream(streamId)
    }

    override suspend fun getMessagesFromTopic(
        streamId: Int,
        topicName: String,
    ): List<MessageLocal> {
        return messagesDao.getAllFromTopic(streamId, topicName)
    }

    override suspend fun getMessage(messageId: Int): MessageLocal {
        return messagesDao.getMessage(messageId)
    }

    override suspend fun insertMessagesToStream(list: List<MessageLocal>, streamId: Int) {
        val storageAmount = STORAGE_MAXIMUM - numberOfMessagesInStream(streamId)
        messagesDao.insertList(list.takeLast(storageAmount))
    }

    override suspend fun insertMessage(message: MessageLocal) {
        messagesDao.insert(message)
    }

    override suspend fun deleteAll() {
        messagesDao.deleteAll()
    }

    private suspend fun numberOfMessagesInStream(streamId: Int): Int {
        return messagesDao.countMessagesInStream(streamId)
    }

    companion object {
        private const val STORAGE_MAXIMUM = 50
    }
}