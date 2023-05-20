package com.example.feature_profile.impl.domain.usecase.impl

import com.example.feature_profile.impl.domain.repository.ProfileRepository
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase
import javax.inject.Inject

internal class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : GetProfileUseCase {
    override operator fun invoke(userId: Int) =
        profileRepository.getProfile(userId)
}