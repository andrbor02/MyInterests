package com.example.feature_authorization_ui.api

import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.feature_authorization.impl.di.AuthorizationComponentHolder
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase

internal interface AuthorizationUiDependencies {
    fun router(): RouterForAuthorization

    fun checkOrganizationUseCase(): CheckOrganizationUseCase

    fun fetchApiKeyUseCase(): FetchApiKeyUseCase

    class Impl : AuthorizationUiDependencies {

        override fun router(): RouterForAuthorization {
            return NavigationComponentHolder.get().routerForAuthorization()
        }

        override fun checkOrganizationUseCase(): CheckOrganizationUseCase {
            return AuthorizationComponentHolder.get().checkOrganizationUseCase()
        }

        override fun fetchApiKeyUseCase(): FetchApiKeyUseCase {
            return AuthorizationComponentHolder.get().fetchApiKeyUseCase()
        }

    }
}