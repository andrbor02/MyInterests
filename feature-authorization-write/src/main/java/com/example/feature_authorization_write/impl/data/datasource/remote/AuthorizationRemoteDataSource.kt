package com.example.feature_authorization_write.impl.data.datasource.remote

import com.example.feature_people.impl.data.datasource.remote.model.users.ApiKeyResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import kotlinx.coroutines.flow.Flow

internal interface AuthorizationRemoteDataSource {

    fun getUsers(): Flow<ApiKeyResponse>

    fun getUsersPresence(): Flow<UsersPresenceResponse>
}