package com.example.feature_profile_ui.api

import com.example.core_data.impl.account.AccountController
import com.example.core_data.impl.di.DataComponentHolder
import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForProfile
import com.example.feature_profile.impl.di.ProfileComponentHolder
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase

internal interface ProfileUiDependencies {

    fun getProfileUseCase(): GetProfileUseCase

    fun accountController(): AccountController

    fun routerForProfile(): RouterForProfile

    class Impl : ProfileUiDependencies {
        override fun getProfileUseCase(): GetProfileUseCase {
            return ProfileComponentHolder.get().getProfileUseCase()
        }

        override fun accountController(): AccountController {
            return DataComponentHolder.get().accountController()
        }

        override fun routerForProfile(): RouterForProfile {
            return NavigationComponentHolder.get().routerForProfile()
        }
    }
}