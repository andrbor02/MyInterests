package com.example.`feature_authorization-read`.impl.domain.usecase.impl

import com.example.feature_authorization_read.impl.domain.repository.AuthorizationRepository
import com.example.`feature_authorization-read`.impl.domain.usecase.GetApiKeyUseCase
import javax.inject.Inject

internal class GetApiKeyUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : GetApiKeyUseCase {
    override suspend fun invoke(): String {
        return authorizationRepository.getApiKey()
    }
}