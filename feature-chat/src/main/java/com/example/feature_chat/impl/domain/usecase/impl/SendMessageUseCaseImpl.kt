package com.example.feature_chat.impl.domain.usecase.impl

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase
import javax.inject.Inject

internal class SendMessageUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : SendMessageUseCase {
    override suspend operator fun invoke(sendMessageModel: SendMessageModel): MessageModel {
        return chatRepository.sendMessage(sendMessageModel)
    }
}