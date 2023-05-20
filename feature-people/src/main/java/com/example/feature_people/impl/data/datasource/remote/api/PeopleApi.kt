package com.example.feature_people.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_people.impl.data.datasource.remote.model.users.UsersResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import retrofit2.http.GET

internal interface PeopleApi {

    @GET("users")
    suspend fun getUsers(): GenericResponse<UsersResponse>

    @GET("realm/presence")
    suspend fun getUsersPresence(): GenericResponse<UsersPresenceResponse>
}