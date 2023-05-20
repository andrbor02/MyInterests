package com.example.feature_chat.impl.domain.repository

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import kotlinx.coroutines.flow.Flow

internal interface ChatRepository {

    fun getMessagesFromTopic(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): Flow<List<MessageModel>>

    fun getMessagesFromStream(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): Flow<List<MessageModel>>

    suspend fun sendMessage(sendMessageModel: SendMessageModel): MessageModel

    suspend fun addReaction(chatPath: ChatPath, messageId: Int, reactionName: String): MessageModel

    suspend fun removeReaction(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel
}