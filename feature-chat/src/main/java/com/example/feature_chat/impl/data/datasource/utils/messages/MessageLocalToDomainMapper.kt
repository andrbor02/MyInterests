package com.example.feature_chat.impl.data.datasource.utils.messages

import com.example.core_utils.common_helpers.getDateFromTimestamp
import com.example.core_utils.common_helpers.getTimeFromTimestamp
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal
import com.example.feature_chat.impl.data.datasource.local.model.ReactionLocal
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionLocalToDomainMapper
import com.example.feature_chat.impl.domain.model.MessageModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal interface MessageLocalToDomainMapper {
    operator fun invoke(messageLocal: MessageLocal): MessageModel
}

internal class MessageLocalToDomainMapperImpl @Inject constructor(
    private val reactionLocalToDomainMapper: ReactionLocalToDomainMapper,
) :
    MessageLocalToDomainMapper {
    override operator fun invoke(messageLocal: MessageLocal): MessageModel {
        val reactionsDeserialized = try {
            Json.decodeFromString<List<ReactionLocal>>(messageLocal.reactions)
        } finally {
            emptyList<ReactionLocal>()
        }

        val reactionsDomain = reactionsDeserialized.map { reactionLocal ->
            reactionLocalToDomainMapper(reactionLocal)
        }

        return MessageModel(
            id = messageLocal.id,
            senderId = messageLocal.senderId,
            avatar = messageLocal.avatar,
            name = messageLocal.name,
            time = messageLocal.timestamp.getTimeFromTimestamp(),
            topic = messageLocal.topic,
            text = messageLocal.text,
            date = messageLocal.timestamp.getDateFromTimestamp(),
            timestamp = messageLocal.timestamp,
            reactions = reactionsDomain,
        )
    }
}