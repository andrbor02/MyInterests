package com.example.feature_authorization.impl.domain.usecase

interface CheckOrganizationUseCase {
    suspend operator fun invoke(organization: String): Boolean
}