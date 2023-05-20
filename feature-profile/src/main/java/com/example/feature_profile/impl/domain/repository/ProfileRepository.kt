package com.example.feature_profile.impl.domain.repository

import com.example.feature_profile.impl.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

internal interface ProfileRepository {

    fun getProfile(userId: Int): Flow<ProfileModel>
}