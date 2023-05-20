package com.example.feature_chat.impl.data.datasource.utils.messages

import com.example.core_utils_android.extensions.fromHtmlToString
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageRemote
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToLocalMapper
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal interface MessagesRemoteToLocalMapper {
    operator fun invoke(
        messageRemote: MessageRemote,
        streamId: Int,
        topicName: String,
    ): MessageLocal
}

internal class MessagesRemoteToLocalMapperImpl @Inject constructor(
    private val reactionRemoteToLocalMapper: ReactionRemoteToLocalMapper,
) :
    MessagesRemoteToLocalMapper {
    override operator fun invoke(
        messageRemote: MessageRemote,
        streamId: Int,
        topicName: String,
    ): MessageLocal {
        val reactionsLocal = reactionRemoteToLocalMapper(messageRemote.reactions)
        val reactionsSerialized = Json.encodeToString(reactionsLocal)

        return MessageLocal(
            id = messageRemote.id,
            senderId = messageRemote.senderId,
            avatar = messageRemote.avatar,
            name = messageRemote.senderFullName,
            timestamp = messageRemote.timestamp,
            topic = messageRemote.topic,
            text = messageRemote.content.fromHtmlToString(),
            reactions = reactionsSerialized,
            streamId = streamId,
            topicName = topicName,
        )
    }
}