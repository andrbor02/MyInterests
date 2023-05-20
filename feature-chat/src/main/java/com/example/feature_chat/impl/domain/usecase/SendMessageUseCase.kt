package com.example.feature_chat.impl.domain.usecase

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.SendMessageModel

interface SendMessageUseCase {
    suspend operator fun invoke(sendMessageModel: SendMessageModel): MessageModel
}