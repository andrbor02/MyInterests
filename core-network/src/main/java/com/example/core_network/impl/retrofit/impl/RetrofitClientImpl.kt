package com.example.core_network.impl.retrofit.impl

import com.example.core_network.impl.retrofit.NetworkClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitClientImpl @Inject constructor(
    private val retrofit: Retrofit,
) : NetworkClient {

    override fun <T> getApi(api: Class<T>): T {
        return retrofit.create(api)
    }
}