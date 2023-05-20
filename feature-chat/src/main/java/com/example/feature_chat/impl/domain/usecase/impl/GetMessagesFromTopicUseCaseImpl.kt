package com.example.feature_chat.impl.domain.usecase.impl

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetMessagesFromTopicUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : GetMessagesFromTopicUseCase {
    override operator fun invoke(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): Flow<List<MessageModel>> {
        return chatRepository.getMessagesFromTopic(chatPath, loadSettings)
    }
}