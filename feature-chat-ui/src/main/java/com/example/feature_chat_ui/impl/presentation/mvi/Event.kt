package com.example.feature_chat_ui.impl.presentation.mvi

import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

internal sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        class LoadChat(
            val showAllTopics: Boolean,
            val chatPath: ChatPath,
        ) : Ui()

        class SendMessage(val sendMessageModel: SendMessageModel) : Ui()
        class AddReaction(val chatPath: ChatPath, val messageId: Int, val emojiName: String) : Ui()
        class RemoveReaction(val chatPath: ChatPath, val messageId: Int, val emojiName: String) :
            Ui()
    }

    sealed class Internal : Event() {
        object StartLoading : Internal()
        class ChatLoaded(val value: List<DelegateItem>) : Internal()
        object LoadingError : Internal()
    }
}