package com.example.feature_chat.impl.di

import com.example.feature_chat.api.ChatComponent
import com.example.feature_chat.api.ChatDependencies
import dagger.Component

@Component(
    dependencies = [ChatDependencies::class],
    modules = [ChatModule::class]
)
internal interface ChatComponentimpl : ChatComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChatDependencies): ChatComponent
    }
}