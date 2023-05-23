package com.example.feature_authorization.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.core_network.impl.retrofit.BaseUrlHolder
import com.example.core_network.impl.retrofit.CredentialsHolder
import com.example.feature_authorization.impl.data.datasource.remote.api.AuthorizationApi

internal interface AuthorizationDependencies {

    fun applicationContext(): Context

    fun baseUrlHolder(): BaseUrlHolder

    fun credentialsHolder(): CredentialsHolder

    class Impl : AuthorizationDependencies {
        override fun applicationContext(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }

        override fun baseUrlHolder(): BaseUrlHolder {
            return NetworkComponentHolder.get().baseUrlHolder()
        }

        override fun credentialsHolder(): CredentialsHolder {
            return NetworkComponentHolder.get().CredentialsHolder()
        }
    }
}

