package com.example.feature_chat_ui.impl.presentation.stateholders

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core_utils.SERVICE_LOG
import com.example.core_utils.common_helpers.replaceItem
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils.common_helpers.updateSetByReplacing
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromStreamUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase
import com.example.feature_chat_ui.impl.presentation.model.ChatSet
import com.example.feature_chat_ui.impl.presentation.mvi.ChatScreenState
import com.example.feature_chat_ui.impl.presentation.mvi.Command
import com.example.feature_chat_ui.impl.presentation.mvi.Event
import com.example.feature_chat_ui.impl.presentation.mvi.Reducer
import com.example.feature_chat_ui.impl.presentation.utils.ChatCombiner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.util.TreeSet

internal class ChatViewModel(
    private val getMessagesFromTopicUseCase: GetMessagesFromTopicUseCase,
    private val getMessagesFromStreamUseCase: GetMessagesFromStreamUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val addReactionUseCase: AddReactionUseCase,
    private val removeReactionUseCase: RemoveReactionUseCase,
    private val chatCombiner: ChatCombiner,
    initialState: ChatScreenState,
    reducer: Reducer,
) : MviViewModel<ChatScreenState, Event, Event.Ui, Event.Internal, Command>(initialState, reducer) {

    private val timestampComparator = Comparator.comparing(MessageModel::timestamp)
    private val messagesSet: TreeSet<MessageModel> = TreeSet(timestampComparator)

    private val _loadChatFlow = MutableSharedFlow<ChatSet>()

    init {
        subscribeToLoadChatChanges()
    }

    override suspend fun actor(command: Command) = when (command) {
        is Command.LoadTopicChat -> _loadChatFlow.emit(
            ChatSet(
                chatSource = getMessagesFromTopicUseCase,
                command.chatPath,
                defineLoadSettings(),
            )
        )

        is Command.LoadStreamChat -> {
            _loadChatFlow.emit(
                ChatSet(
                    chatSource = getMessagesFromStreamUseCase,
                    command.chatPath,
                    defineLoadSettings(),
                )
            )
        }

        is Command.SendMessage -> sendMessage(command.sendMessageModel)
        is Command.AddReaction -> addReaction(
            command.chatPath,
            command.messageId,
            command.emojiName
        )

        is Command.RemoveReaction -> removeReaction(
            command.chatPath,
            command.messageId,
            command.emojiName
        )
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeToLoadChatChanges() {
        _loadChatFlow
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(loadChat(it)) } }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private fun addReaction(chatPath: ChatPath, messageId: Int, emojiName: String) {
        viewModelScope.launch {
            val updatedMessage = runCatchingNonCancellation {
                addReactionUseCase(chatPath, messageId, emojiName)
            }
            updateSingleMessage(updatedMessage)
        }
    }

    private fun removeReaction(chatPath: ChatPath, messageId: Int, emojiName: String) {
        viewModelScope.launch {
            val updatedMessage = runCatchingNonCancellation {
                removeReactionUseCase(chatPath, messageId, emojiName)
            }
            updateSingleMessage(updatedMessage)
        }
    }

    private fun loadChat(chatSet: ChatSet) {
        sendInternalEvent(Event.Internal.StartLoading)
        viewModelScope.launch {
            chatSet.chatSource(chatSet.chatPath, chatSet.loadSettings)
                .catch { exception: Throwable ->
                    sendInternalEvent(Event.Internal.LoadingError)
                }
                .collect { chat ->
                    messagesSet.updateSetByReplacing(chat) { existingItem, newItem ->
                        existingItem.id == newItem.id
                    }
                    sendInternalEvent(Event.Internal.ChatLoaded(chatCombiner(messagesSet.toList())))
                }
        }
    }

    private fun updateSingleMessage(updatedMessage: Result<MessageModel>) {
        updatedMessage.fold(
            onSuccess = { message ->
                val oldMessage = messagesSet.find { it.id == message.id }
                if (oldMessage != null) {
                    messagesSet.replaceItem(oldMessage, message)
                    sendInternalEvent(Event.Internal.ChatLoaded(chatCombiner(messagesSet.toList())))
                }
            },
            onFailure = { exception ->
                Log.e(SERVICE_LOG, exception.message.toString())
            }
        )
    }

    private fun addSingleMessage(updatedMessage: Result<MessageModel>) {
        updatedMessage.fold(
            onSuccess = { message ->
                messagesSet.add(message)
                sendInternalEvent(Event.Internal.ChatLoaded(chatCombiner(messagesSet.toList())))
            },
            onFailure = { exception ->
                Log.e(SERVICE_LOG, exception.message.toString())
            }
        )
    }

    private fun sendMessage(sendMessageModel: SendMessageModel) {
        if (sendMessageModel.text.isBlank()) {
            return
        }

        viewModelScope.launch {
            val updatedMessage = runCatchingNonCancellation {
                sendMessageUseCase(sendMessageModel)
            }
            addSingleMessage(updatedMessage)
        }
    }

    private fun defineLoadSettings(): LoadSettings {
        return if (messagesSet.isEmpty()) {
            LoadSettings.loadInitial()
        } else {
            LoadSettings.loadUp(messagesSet.first().id)
        }
    }
}