package com.example.feature_authorization_read.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.`feature_authorization-read`.impl.domain.usecase.FetchApiKeyUseCase
import com.example.`feature_authorization-read`.impl.domain.usecase.GetAccountUseCase
import com.example.`feature_authorization-read`.impl.domain.usecase.GetApiKeyUseCase
import com.example.feature_authorization_read.impl.domain.usecase.LogoutUseCase

interface AuthorizationComponent : DiComponent {

    fun fetchApiKeyUseCase(): FetchApiKeyUseCase

    fun getApiKeyUseCase(): GetApiKeyUseCase

    fun getAccountUseCase(): GetAccountUseCase

    fun logoutUseCase(): LogoutUseCase
}