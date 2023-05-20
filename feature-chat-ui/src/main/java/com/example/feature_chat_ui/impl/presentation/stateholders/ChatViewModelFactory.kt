package com.example.feature_chat_ui.impl.presentation.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromStreamUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase
import com.example.feature_chat_ui.impl.presentation.mvi.ChatScreenState
import com.example.feature_chat_ui.impl.presentation.mvi.Reducer
import com.example.feature_chat_ui.impl.presentation.utils.ChatCombiner
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor(
    private val getMessagesFromTopicUseCase: GetMessagesFromTopicUseCase,
    private val getMessagesFromStreamUseCase: GetMessagesFromStreamUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val addReactionUseCase: AddReactionUseCase,
    private val removeReactionUseCase: RemoveReactionUseCase,
    private val chatCombiner: ChatCombiner,
) : ViewModelProvider.Factory {

    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(
            getMessagesFromTopicUseCase = getMessagesFromTopicUseCase,
            getMessagesFromStreamUseCase = getMessagesFromStreamUseCase,
            sendMessageUseCase = sendMessageUseCase,
            addReactionUseCase = addReactionUseCase,
            removeReactionUseCase = removeReactionUseCase,
            chatCombiner = chatCombiner,
            initialState = ChatScreenState.Init,
            reducer = reducer,
        ) as T
    }
}