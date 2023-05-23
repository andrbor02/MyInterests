package com.example.feature_authorization.impl.domain.usecase.impl

import com.example.feature_authorization.impl.domain.repository.AuthorizationRepository
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import javax.inject.Inject

internal class CheckOrganizationUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
): CheckOrganizationUseCase{
    override suspend fun invoke(organization: String): Boolean {
        return authorizationRepository.checkOrganization(organization)
    }
}