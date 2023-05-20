package com.example.feature_chat.impl.data.repository_impl

import com.example.feature_chat.impl.data.datasource.local.MessagesLocalDataSource
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal
import com.example.feature_chat.impl.data.datasource.remote.MessagesRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.ReactionsRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageRemote
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageLocalToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageRemoteToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.messages.MessagesRemoteToLocalMapper
import com.example.feature_chat.impl.data.datasource.utils.reactions.SuccessfulOperationChecker
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.Anchor
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.model.utils.LoadSettings
import com.example.feature_chat.impl.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
    private val messagesRemoteDataSource: MessagesRemoteDataSource,
    private val reactionsRemoteDataSource: ReactionsRemoteDataSource,
    private val messagesLocalDataSource: MessagesLocalDataSource,
    private val messageLocalToDomainMapper: MessageLocalToDomainMapper,
    private val messageRemoteToDomainMapper: MessageRemoteToDomainMapper,
    private val messagesRemoteToLocalMapper: MessagesRemoteToLocalMapper,
    private val successfulOperationChecker: SuccessfulOperationChecker,
) : ChatRepository {
    override fun getMessagesFromTopic(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): Flow<List<MessageModel>> = getMessages(
        localSource = {
            messagesLocalDataSource.getMessagesFromTopic(chatPath.streamId, chatPath.topicName)
        },
        remoteSource = {
            messagesRemoteDataSource.getMessagesFromTopic(
                chatPath,
                loadSettings
            ).messages
        },
        chatPath, loadSettings
    )

    override fun getMessagesFromStream(
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ): Flow<List<MessageModel>> = getMessages(
        localSource = {
            messagesLocalDataSource.getMessagesFromStream(chatPath.streamId)
        },
        remoteSource = {
            messagesRemoteDataSource.getMessagesFromStream(
                chatPath.streamId,
                loadSettings
            ).messages
        },
        chatPath, loadSettings
    )

    private fun getMessages(
        localSource: suspend () -> List<MessageLocal>,
        remoteSource: suspend () -> List<MessageRemote>,
        chatPath: ChatPath,
        loadSettings: LoadSettings,
    ) = flow {
        if (loadSettings.anchor == Anchor.Special("newest")) {
            val localList = localSource()
            if (localList.isNotEmpty()) {
                emit(localList.map { messageLocalToDomainMapper(it) })
            }
        }
        val remoteList = remoteSource()
        emit(remoteList.map { messageRemoteToDomainMapper(it) })
        updateDatabase(remoteList, chatPath)
    }.flowOn(Dispatchers.Default)

    private suspend fun updateDatabase(list: List<MessageRemote>, chatPath: ChatPath) {
        messagesLocalDataSource.insertMessagesToStream(
            list.map { messageRemote ->
                messagesRemoteToLocalMapper(
                    messageRemote = messageRemote,
                    streamId = chatPath.streamId,
                    topicName = chatPath.topicName,
                )
            },
            chatPath.streamId
        )
    }

    override suspend fun sendMessage(sendMessageModel: SendMessageModel): MessageModel {
        val newId = messagesRemoteDataSource.sendMessage(
            stream = sendMessageModel.stream,
            topic = sendMessageModel.topic,
            text = sendMessageModel.text,
        ).newMessageId

        val newMessageRemote = messagesRemoteDataSource.getSingleMessage(newId).message
        return messageRemoteToDomainMapper(newMessageRemote)
    }

    override suspend fun addReaction(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel = doAndGetUpdatedMessage(messageId) {
        successfulOperationChecker(
            reactionsRemoteDataSource.addReaction(messageId, reactionName)
        )
    }

    override suspend fun removeReaction(
        chatPath: ChatPath,
        messageId: Int,
        reactionName: String,
    ): MessageModel = doAndGetUpdatedMessage(messageId) {
        successfulOperationChecker(
            reactionsRemoteDataSource.removeReaction(messageId, reactionName)
        )
    }

    private suspend fun doAndGetUpdatedMessage(
        messageId: Int,
        action: suspend () -> Boolean,
    ): MessageModel {
        val isSuccessful = action()
        return if (isSuccessful) {
            val messageRemote = messagesRemoteDataSource.getSingleMessage(messageId).message
            messageRemoteToDomainMapper(messageRemote)
        } else {
            throw Exception("Add reaction error")
        }
    }
}