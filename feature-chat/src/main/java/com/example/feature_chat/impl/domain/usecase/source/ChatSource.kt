package com.example.feature_chat.impl.domain.usecase.source

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import kotlinx.coroutines.flow.Flow

interface ChatSource {
    operator fun invoke(chatPath: ChatPath, loadSettings: LoadSettings): Flow<List<MessageModel>>
}