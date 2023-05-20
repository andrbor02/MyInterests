package com.example.feature_chat_ui.impl.presentation.mvi

import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

internal sealed class ChatScreenState {
    object Init : ChatScreenState()
    data class Success(val data: List<DelegateItem>) : ChatScreenState()
}