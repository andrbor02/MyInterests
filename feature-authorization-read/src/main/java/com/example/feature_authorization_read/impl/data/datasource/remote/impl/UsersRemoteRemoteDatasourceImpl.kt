package com.example.feature_people.impl.data.datasource.remote.impl

import com.example.feature_people.impl.data.datasource.remote.UsersRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.api.PeopleApi
import com.example.feature_people.impl.data.datasource.remote.model.users.UsersResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UsersRemoteRemoteDatasourceImpl @Inject constructor(
    private val peopleApi: PeopleApi,
) : UsersRemoteDataSource {
    override fun getUsers(): Flow<UsersResponse> = flow {
        emit(peopleApi.getUsers().handle())
    }

    override fun getUsersPresence(): Flow<UsersPresenceResponse> = flow {
        emit(peopleApi.getUsersPresence().handle())
    }
}