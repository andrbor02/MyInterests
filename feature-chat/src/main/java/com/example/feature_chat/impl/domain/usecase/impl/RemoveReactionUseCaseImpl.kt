package com.example.feature_chat.impl.domain.usecase.impl

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import javax.inject.Inject

internal class RemoveReactionUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : RemoveReactionUseCase {
    override suspend fun invoke(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel {
        return chatRepository.removeReaction(chatPath, messageId, reactionName)
    }
}