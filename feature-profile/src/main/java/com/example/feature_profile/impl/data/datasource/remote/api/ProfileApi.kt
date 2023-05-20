package com.example.feature_profile.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_profile.impl.data.datasource.remote.model.user.UserResponse
import com.example.feature_profile.impl.data.datasource.remote.model.user_presence.UserPresenceResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProfileApi {

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") id: Int): GenericResponse<UserResponse>

    @GET("users/{userId}/presence")
    suspend fun getUserPresence(@Path("userId") id: Int): GenericResponse<UserPresenceResponse>
}