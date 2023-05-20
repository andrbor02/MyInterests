package com.example.feature_profile.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase

interface ProfileComponent : DiComponent {
    fun getProfileUseCase(): GetProfileUseCase
}