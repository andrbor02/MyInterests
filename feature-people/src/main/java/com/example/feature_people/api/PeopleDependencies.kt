package com.example.feature_people.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.feature_people.impl.data.datasource.remote.api.PeopleApi

internal interface PeopleDependencies {

    fun applicationContext(): Context

    fun peopleApi(): PeopleApi

    class Impl : PeopleDependencies {
        override fun applicationContext(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }

        override fun peopleApi(): PeopleApi {
            return NetworkComponentHolder.get().networkClient().getApi(PeopleApi::class.java)
        }
    }
}