package com.example.`feature_authorization-read`.impl.domain.usecase.impl

import com.example.`feature_authorization-read`.impl.domain.repository.AuthorizationRepository
import com.example.`feature_authorization-read`.impl.domain.usecase.FetchApiKeyUseCase
import javax.inject.Inject

internal class FetchApiKeyUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : FetchApiKeyUseCase {
    override suspend fun invoke(email: String, password: String): Boolean {
        return authorizationRepository.fetchApiKey(email, password)
    }
}