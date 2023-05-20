package com.example.feature_people.impl.data.datasource.remote.impl

import com.example.feature_authorization_write.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.api.AuthorizationApi
import com.example.feature_people.impl.data.datasource.remote.model.users.ApiKeyResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UsersRemoteRemoteDatasourceImpl @Inject constructor(
    private val authorizationApi: AuthorizationApi,
) : AuthorizationRemoteDataSource {
    override fun getUsers(): Flow<ApiKeyResponse> = flow {
        emit(authorizationApi.getUsers().handle())
    }

    override fun getUsersPresence(): Flow<UsersPresenceResponse> = flow {
        emit(authorizationApi.getUsersPresence().handle())
    }
}