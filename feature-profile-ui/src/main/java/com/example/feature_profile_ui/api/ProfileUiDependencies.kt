package com.example.feature_profile_ui.api

import com.example.feature_profile.impl.di.ProfileComponentHolder
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase

internal interface ProfileUiDependencies {

    fun getProfileUseCase(): GetProfileUseCase

    class Impl : ProfileUiDependencies {
        override fun getProfileUseCase(): GetProfileUseCase {
            return ProfileComponentHolder.get().getProfileUseCase()
        }
    }
}