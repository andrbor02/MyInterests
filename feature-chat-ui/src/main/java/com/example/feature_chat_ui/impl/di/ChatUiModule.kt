package com.example.feature_chat_ui.impl.di

import com.example.feature_chat_ui.impl.presentation.utils.ChatCombiner
import com.example.feature_chat_ui.impl.presentation.utils.ChatCombinerImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ChatUiModule.Bindings::class]
)
internal class ChatUiModule {

    @Module
    interface Bindings {
        @Binds
        fun bindChatCombiner(chatCombinerImpl: ChatCombinerImpl): ChatCombiner
    }
}