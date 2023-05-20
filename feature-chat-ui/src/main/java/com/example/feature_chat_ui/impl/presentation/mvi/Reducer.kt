package com.example.feature_chat_ui.impl.presentation.mvi

import com.example.core_utils_android.mvi.MviReducer
import com.example.feature_chat_ui.impl.presentation.model.loading.LoadingDelegateItem

internal class Reducer : MviReducer<ChatScreenState, Event, Event.Ui, Event.Internal, Command>() {
    override fun reduce(state: ChatScreenState, event: Event): ChatScreenState =
        when (event) {
            is Event.Ui.Init -> {
                state
            }

            is Event.Ui.LoadChat -> {
                if (event.showAllTopics) {
                    sendCommandToActor(Command.LoadStreamChat(event.chatPath))
                } else {
                    sendCommandToActor(Command.LoadTopicChat(event.chatPath))
                }
                state
            }

            is Event.Ui.SendMessage -> {
                sendCommandToActor(Command.SendMessage(event.sendMessageModel))
                state
            }

            is Event.Ui.AddReaction -> {
                sendCommandToActor(
                    Command.AddReaction(
                        event.chatPath,
                        event.messageId,
                        event.emojiName
                    )
                )
                state
            }

            is Event.Ui.RemoveReaction -> {
                sendCommandToActor(
                    Command.RemoveReaction(
                        event.chatPath,
                        event.messageId,
                        event.emojiName
                    )
                )
                state
            }

            is Event.Internal.StartLoading -> {
                if (state is ChatScreenState.Success) {
                    ChatScreenState.Success(listOf(LoadingDelegateItem()) + state.data)
                } else {
                    ChatScreenState.Success(listOf(LoadingDelegateItem()))
                }
            }

            is Event.Internal.ChatLoaded -> {
                ChatScreenState.Success(event.value)
            }

            Event.Internal.LoadingError -> {
                state
            }
        }
}