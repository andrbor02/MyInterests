package com.example.feature_chat.impl.di

import com.example.core_utils.di_helpers.component_holder.DataComponentHolder
import com.example.feature_chat.api.ChatComponent
import com.example.feature_chat.api.ChatDependencies

object ChatComponentHolder : DataComponentHolder<ChatComponent>() {
    override fun build(): ChatComponent =
        DaggerChatComponentimpl.factory().create(
            ChatDependencies.Impl()
        )
}