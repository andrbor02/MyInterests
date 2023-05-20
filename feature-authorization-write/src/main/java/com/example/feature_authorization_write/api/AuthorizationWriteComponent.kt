package com.example.feature_authorization_write.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_authorization_write.impl.domain.usecase.GetAccountUseCase
import com.example.feature_authorization_write.impl.domain.usecase.GetApiKeyUseCase
import com.example.feature_authorization_write.impl.domain.usecase.LogoutUseCase

interface AuthorizationWriteComponent : DiComponent {

    fun fetchApiKeyUseCase(): FetchApiKeyUseCase
}