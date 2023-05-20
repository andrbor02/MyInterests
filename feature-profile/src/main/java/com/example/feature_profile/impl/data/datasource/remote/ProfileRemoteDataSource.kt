package com.example.feature_profile.impl.data.datasource.remote

import com.example.feature_profile.impl.data.datasource.remote.model.user.UserResponse
import com.example.feature_profile.impl.data.datasource.remote.model.user_presence.UserPresenceResponse
import kotlinx.coroutines.flow.Flow

internal interface ProfileRemoteDataSource {

    fun getUser(userId: Int): Flow<UserResponse>

    fun getUserPresence(userId: Int): Flow<UserPresenceResponse>
}