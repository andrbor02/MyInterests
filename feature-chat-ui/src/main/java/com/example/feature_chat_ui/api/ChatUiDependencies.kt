package com.example.feature_chat_ui.api

import com.example.feature_chat.impl.di.ChatComponentHolder
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromStreamUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase
import com.example.feature_streams.impl.di.StreamsComponentHolder
import com.example.feature_streams.impl.domain.usecase.GetTopicsUseCase

interface ChatUiDependencies {
    fun getMessagesFromTopicUseCase(): GetMessagesFromTopicUseCase

    fun getMessagesFromStreamUseCase(): GetMessagesFromStreamUseCase

    fun getTopicsUseCase(): GetTopicsUseCase

    fun sendMessageUseCase(): SendMessageUseCase

    fun addReactionUseCase(): AddReactionUseCase

    fun removeReactionUseCase(): RemoveReactionUseCase

    class Impl : ChatUiDependencies {
        override fun getMessagesFromTopicUseCase(): GetMessagesFromTopicUseCase {
            return ChatComponentHolder.get().getMessagesFromTopicUseCase()
        }

        override fun getMessagesFromStreamUseCase(): GetMessagesFromStreamUseCase {
            return ChatComponentHolder.get().getMessagesFromStreamUseCase()
        }

        override fun getTopicsUseCase(): GetTopicsUseCase {
            return StreamsComponentHolder.get().getTopicsUseCase()
        }

        override fun sendMessageUseCase(): SendMessageUseCase {
            return ChatComponentHolder.get().sendMessageUseCase()
        }

        override fun addReactionUseCase(): AddReactionUseCase {
            return ChatComponentHolder.get().addReactionUseCase()
        }

        override fun removeReactionUseCase(): RemoveReactionUseCase {
            return ChatComponentHolder.get().removeReactionUseCase()
        }
    }
}