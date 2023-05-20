package com.example.feature_chat.impl.domain.usecase

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath

interface AddReactionUseCase {
    suspend operator fun invoke(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel
}