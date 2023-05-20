package com.example.feature_chat.impl.data.datasource.remote.impl

import com.example.core_utils_android.serialization.serializeListOfMaps
import com.example.feature_chat.impl.data.datasource.remote.MessagesRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.api.MessagesApi
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessagesResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.SendMessageResponse
import com.example.feature_chat.impl.domain.model.utils.Anchor
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import javax.inject.Inject

internal class MessagesRemoteDataSourceImpl @Inject constructor(
    private val messagesApi: MessagesApi,
) : MessagesRemoteDataSource {
    override suspend fun getMessagesFromTopic(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): MessagesResponse {
        val narrow = serializeListOfMaps(
            listOf(
                hashMapOf(
                    OPERATOR to STREAM, OPERAND to chatPath.streamId
                ),
                hashMapOf(
                    OPERATOR to TOPIC, OPERAND to chatPath.topicName
                )
            )
        )

        return sendNarrow(loadSettings, narrow)
    }

    override suspend fun getMessagesFromStream(
        streamId: Int,
        loadSettings: LoadSettings,
    ): MessagesResponse {
        val narrow = serializeListOfMaps(
            listOf(
                hashMapOf(
                    OPERATOR to STREAM, OPERAND to streamId
                )
            )
        )

        return sendNarrow(loadSettings, narrow)
    }

    override suspend fun getSingleMessage(messageId: Int): MessageResponse {
        return messagesApi.getSingleMessage(messageId).handle()
    }

    override suspend fun sendMessage(
        stream: String,
        topic: String,
        text: String,
    ): SendMessageResponse {
        return messagesApi.sendMessage(stream, topic, text).handle()
    }

    private suspend fun sendNarrow(loadSettings: LoadSettings, narrow: String) =
        when (loadSettings.anchor) {
            is Anchor.MessageId ->
                messagesApi.getMessagesByMessageId(
                    narrow = narrow,
                    beforeAnchor = loadSettings.beforeAnchor,
                    afterAnchor = loadSettings.afterAnchor,
                    messageId = loadSettings.anchor.id
                ).handle().apply {
                    this.messages.dropLast(1)
                }

            is Anchor.Special ->
                messagesApi.getMessagesByAnchor(
                    narrow = narrow,
                    beforeAnchor = loadSettings.beforeAnchor,
                    afterAnchor = loadSettings.afterAnchor,
                    anchorValue = loadSettings.anchor.value
                ).handle()
        }

    companion object {
        private const val OPERAND = "operand"
        private const val OPERATOR = "operator"

        private const val STREAM = "stream"
        private const val TOPIC = "topic"
    }
}