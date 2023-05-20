package com.example.feature_profile.api

import com.example.core_network.impl.di.NetworkComponentHolder
import com.example.feature_profile.impl.data.datasource.remote.api.ProfileApi

internal interface ProfileDependencies {
    fun profileApi(): ProfileApi

    class Impl : ProfileDependencies {
        override fun profileApi(): ProfileApi {
            return NetworkComponentHolder.get().networkClient().getApi(ProfileApi::class.java)
        }
    }
}