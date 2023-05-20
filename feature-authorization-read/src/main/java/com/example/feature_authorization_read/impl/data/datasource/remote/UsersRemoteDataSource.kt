package com.example.feature_people.impl.data.datasource.remote

import com.example.feature_people.impl.data.datasource.remote.model.users.UsersResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import kotlinx.coroutines.flow.Flow

internal interface UsersRemoteDataSource {

    fun getUsers(): Flow<UsersResponse>

    fun getUsersPresence(): Flow<UsersPresenceResponse>
}