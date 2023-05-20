package com.example.feature_profile.impl.data.repository_impl

import com.example.feature_profile.impl.data.datasource.remote.ProfileRemoteDataSource
import com.example.feature_profile.impl.data.datasource.remote.model.user.UserResponse
import com.example.feature_profile.impl.data.datasource.remote.model.user_presence.UserPresenceResponse
import com.example.feature_profile.impl.data.utils.UserWithPresenceNetworkMerger
import com.example.feature_profile.impl.domain.model.ProfileModel
import com.example.feature_profile.impl.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val userWithPresenceNetworkMerger: UserWithPresenceNetworkMerger,
) : ProfileRepository {
    override fun getProfile(userId: Int): Flow<ProfileModel> =
        profileRemoteDataSource.getUserPresence(userId).combine(
            profileRemoteDataSource.getUser(userId)
        ) { userPresence: UserPresenceResponse, user: UserResponse ->
            userWithPresenceNetworkMerger(
                user.user,
                userPresence.presence
            )
        }.flowOn(Dispatchers.Default)
}