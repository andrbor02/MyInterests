package com.example.feature_authorization.impl.data.datasource.remote.client

import com.example.feature_authorization.impl.data.datasource.remote.api.AuthorizationApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

internal class RetrofitClient @AssistedInject constructor(
    @Assisted baseUrl: String,
    private val callAdapterFactory: CallAdapter.Factory,
    private val okHttpClient: OkHttpClient,
    private val converterFactory: Converter.Factory,
) {

    fun getAuthorizationApi(baseUrl: String): AuthorizationApi {
        val client = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()

        return client.create(AuthorizationApi::class.java)
    }
}