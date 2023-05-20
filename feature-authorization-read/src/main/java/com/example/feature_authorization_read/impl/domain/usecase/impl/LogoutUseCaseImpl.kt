package com.example.`feature_authorization-read`.impl.domain.usecase.impl

import com.example.feature_authorization_read.impl.domain.repository.AuthorizationRepository
import com.example.feature_authorization_read.impl.domain.usecase.LogoutUseCase
import javax.inject.Inject

internal class LogoutUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : LogoutUseCase {
    override suspend fun invoke(): Boolean {
        return authorizationRepository.clearAuthorization()
    }
}