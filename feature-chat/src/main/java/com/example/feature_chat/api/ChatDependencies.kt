package com.example.feature_chat.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.feature_chat.impl.data.datasource.remote.api.MessagesApi
import com.example.feature_chat.impl.data.datasource.remote.api.ReactionsApi

internal interface ChatDependencies {

    fun applicationContext(): Context

    fun messagesApi(): MessagesApi

    fun reactionsApi(): ReactionsApi

    class Impl : ChatDependencies {
        override fun applicationContext(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }

        override fun messagesApi(): MessagesApi {
            return NetworkComponentHolder.get().networkClient().getApi(MessagesApi::class.java)
        }

        override fun reactionsApi(): ReactionsApi {
            return NetworkComponentHolder.get().networkClient().getApi(ReactionsApi::class.java)
        }
    }
}