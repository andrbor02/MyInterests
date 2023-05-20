package com.example.feature_chat.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromStreamUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase

interface ChatComponent : DiComponent {

    fun getMessagesFromTopicUseCase(): GetMessagesFromTopicUseCase

    fun getMessagesFromStreamUseCase(): GetMessagesFromStreamUseCase

    fun sendMessageUseCase(): SendMessageUseCase

    fun addReactionUseCase(): AddReactionUseCase

    fun removeReactionUseCase(): RemoveReactionUseCase
}