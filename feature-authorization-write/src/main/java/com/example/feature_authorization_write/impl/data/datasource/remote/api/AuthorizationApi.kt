package com.example.feature_people.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_people.impl.data.datasource.remote.model.users.ApiKeyResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface AuthorizationApi {

    @FormUrlEncoded
    @POST("users")
    suspend fun fetchApiKey(
        @Field("username") username: String,
        @Field("password") password: String,
    ): GenericResponse<ApiKeyResponse>
}