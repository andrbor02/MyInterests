package com.example.feature_chat.impl.domain.usecase.impl

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import javax.inject.Inject

internal class AddReactionUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : AddReactionUseCase {
    override suspend fun invoke(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel {
        return chatRepository.addReaction(chatPath, messageId, reactionName)
    }
}