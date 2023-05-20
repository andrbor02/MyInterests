package com.example.feature_chat_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.DataComponentHolder
import com.example.feature_chat_ui.api.ChatUiDependencies

object ChatUiComponentHolder : DataComponentHolder<ChatUiComponent>() {
    override fun build(): ChatUiComponent {
        return DaggerChatUiComponent.factory().create(
            ChatUiDependencies.Impl()
        )
    }
}