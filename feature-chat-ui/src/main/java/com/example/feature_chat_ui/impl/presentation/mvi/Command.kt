package com.example.feature_chat_ui.impl.presentation.mvi

import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath

internal sealed class Command {
    class LoadTopicChat(val chatPath: ChatPath) : Command()
    class LoadStreamChat(val chatPath: ChatPath) : Command()
    class SendMessage(val sendMessageModel: SendMessageModel) : Command()
    class AddReaction(val chatPath: ChatPath, val messageId: Int, val emojiName: String) : Command()
    class RemoveReaction(val chatPath: ChatPath, val messageId: Int, val emojiName: String) :
        Command()
}