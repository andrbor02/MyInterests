package com.example.feature_chat.impl.data.datasource.utils.messages

import com.example.core_utils.common_helpers.getDateFromTimestamp
import com.example.core_utils.common_helpers.getTimeFromTimestamp
import com.example.core_utils_android.extensions.fromHtmlToString
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageRemote
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToDomainMapper
import com.example.feature_chat.impl.domain.model.MessageModel
import javax.inject.Inject

internal interface MessageRemoteToDomainMapper {
    operator fun invoke(messageRemote: MessageRemote): MessageModel
}

internal class MessageRemoteToDomainMapperImpl @Inject constructor(
    private val reactionRemoteToDomainMapper: ReactionRemoteToDomainMapper,
) :
    MessageRemoteToDomainMapper {
    override operator fun invoke(messageRemote: MessageRemote): MessageModel {
        return MessageModel(
            id = messageRemote.id,
            senderId = messageRemote.senderId,
            avatar = messageRemote.avatar,
            name = messageRemote.senderFullName,
            time = messageRemote.timestamp.getTimeFromTimestamp(),
            topic = messageRemote.topic,
            text = messageRemote.content.fromHtmlToString(),
            date = messageRemote.timestamp.getDateFromTimestamp(),
            timestamp = messageRemote.timestamp,
            reactions = reactionRemoteToDomainMapper(messageRemote.reactions),
        )
    }
}