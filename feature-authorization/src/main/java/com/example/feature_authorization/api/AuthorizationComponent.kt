package com.example.feature_authorization.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase

interface AuthorizationComponent : DiComponent {

    fun fetchApiKeyUseCase(): FetchApiKeyUseCase

    fun checkOrganizationUseCase(): CheckOrganizationUseCase
}