package com.example.feature_chat.stub

import com.example.feature_chat.impl.data.datasource.remote.MessagesRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageRemote
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessagesResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.SendMessageResponse
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionRemote
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.reactions.EmojiNCU
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings

internal class MessagesRemoteDataSourceStub : MessagesRemoteDataSource {
    override suspend fun getMessagesFromTopic(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): MessagesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMessagesFromStream(
        streamId: Int,
        loadSettings: LoadSettings,
    ): MessagesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleMessage(messageId: Int): MessageResponse {
        return MessageResponse("success", "", stub)
    }

    override suspend fun sendMessage(
        stream: String,
        topic: String,
        text: String,
    ): SendMessageResponse {
        TODO("Not yet implemented")
    }

    companion object {
        val stub = MessageRemote(
            id = 0,
            content = "",
            avatar = null,
            reactions = listOf(ReactionRemote("1f419", "octopus", 0)),
            topic = "",
            senderId = 0,
            timestamp = 0,
            senderFullName = ""
        )

        val stubMessage = MessageModel(
            id = 0,
            senderId = 0,
            avatar = null,
            name = "",
            time = "",
            topic = "",
            text = "",
            date = "",
            timestamp = 0,
            reactions = listOf(ReactionModel(EmojiNCU("octopus", 0x1f419)))
        )
    }
}