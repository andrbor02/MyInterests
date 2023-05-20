package com.example.feature_authorization_write.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.feature_people.impl.data.datasource.remote.api.AuthorizationApi

internal interface AuthorizationWriteDependencies {

    fun applicationContext(): Context

    fun peopleApi(): AuthorizationApi

    class Impl : AuthorizationWriteDependencies {
        override fun applicationContext(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }

        override fun peopleApi(): AuthorizationApi {
            return NetworkComponentHolder.get().networkClient().getApi(AuthorizationApi::class.java)
        }
    }
}

