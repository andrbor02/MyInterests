package com.example.feature_profile.impl.data.datasource.remote.impl

import com.example.feature_profile.impl.data.datasource.remote.ProfileRemoteDataSource
import com.example.feature_profile.impl.data.datasource.remote.api.ProfileApi
import com.example.feature_profile.impl.data.datasource.remote.model.user.UserResponse
import com.example.feature_profile.impl.data.datasource.remote.model.user_presence.UserPresenceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProfileRemoteDatasourceImpl @Inject constructor(
    private val profileApi: ProfileApi,
) : ProfileRemoteDataSource {
    override fun getUser(userId: Int): Flow<UserResponse> = flow {
        emit(profileApi.getUser(userId).handle())
    }

    override fun getUserPresence(userId: Int): Flow<UserPresenceResponse> = flow {
        emit(profileApi.getUserPresence(userId).handle())
    }
}