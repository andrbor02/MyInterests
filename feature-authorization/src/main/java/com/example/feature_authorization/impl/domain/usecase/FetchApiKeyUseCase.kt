package com.example.feature_authorization.impl.domain.usecase

interface FetchApiKeyUseCase {
    suspend operator fun invoke(email: String, password: String)
}