package com.example.feature_profile_ui.api

import com.example.core_data.impl.account.AccountController
import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.di.CoreDataComponentHolder
import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForProfile
import com.example.feature_profile.impl.di.ProfileComponentHolder
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase

internal interface ProfileUiDependencies {

    fun getProfileUseCase(): GetProfileUseCase

    fun accountController(): AccountController

    fun accountPersister(): AccountPersister

    fun routerForProfile(): RouterForProfile

    class Impl : ProfileUiDependencies {
        override fun getProfileUseCase(): GetProfileUseCase {
            return ProfileComponentHolder.get().getProfileUseCase()
        }

        override fun accountController(): AccountController {
            return CoreDataComponentHolder.get().accountController()
        }

        override fun accountPersister(): AccountPersister {
            return CoreDataComponentHolder.get().accountPersister()
        }

        override fun routerForProfile(): RouterForProfile {
            return NavigationComponentHolder.get().routerForProfile()
        }
    }
}