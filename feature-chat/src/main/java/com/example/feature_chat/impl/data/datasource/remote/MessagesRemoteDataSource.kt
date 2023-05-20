package com.example.feature_chat.impl.data.datasource.remote

import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessagesResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.SendMessageResponse
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings

internal interface MessagesRemoteDataSource {

    suspend fun getMessagesFromTopic(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): MessagesResponse

    suspend fun getMessagesFromStream(
        streamId: Int,
        loadSettings: LoadSettings,
    ): MessagesResponse

    suspend fun getSingleMessage(messageId: Int): MessageResponse

    suspend fun sendMessage(
        stream: String,
        topic: String,
        text: String,
    ): SendMessageResponse
}