package com.example.feature_profile.impl.domain.usecase

import com.example.feature_profile.impl.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {
    operator fun invoke(userId: Int): Flow<ProfileModel>
}