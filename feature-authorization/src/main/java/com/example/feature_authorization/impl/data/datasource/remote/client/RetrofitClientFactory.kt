package com.example.feature_authorization.impl.data.datasource.remote.client

import dagger.assisted.AssistedFactory

@AssistedFactory
internal interface RetrofitClientFactory {
    fun create(baseUrl: String): RetrofitClient
}