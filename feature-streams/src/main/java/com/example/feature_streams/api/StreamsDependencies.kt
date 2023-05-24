package com.example.feature_streams.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.di.CoreDataComponentHolder
import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_streams.impl.data.datasource.remote.api.StreamApi

internal interface StreamsDependencies : DiComponent {

    fun applicationContext(): Context

    fun streamApi(): StreamApi

    fun accountPersister(): AccountPersister

    class Impl : StreamsDependencies {
        override fun applicationContext(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }

        override fun streamApi(): StreamApi {
            return NetworkComponentHolder.get().networkClient().getApi(StreamApi::class.java)
        }

        override fun accountPersister(): AccountPersister {
            return CoreDataComponentHolder.get().accountPersister()
        }
    }
}