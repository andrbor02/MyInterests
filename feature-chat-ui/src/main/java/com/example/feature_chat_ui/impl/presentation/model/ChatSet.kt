package com.example.feature_chat_ui.impl.presentation.model

import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import com.example.feature_chat.impl.domain.usecase.source.ChatSource

internal data class ChatSet(
    val chatSource: ChatSource,
    val chatPath: ChatPath,
    val loadSettings: LoadSettings,
)
