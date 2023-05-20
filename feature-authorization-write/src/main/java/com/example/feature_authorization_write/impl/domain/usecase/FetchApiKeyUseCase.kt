package com.example.`feature_authorization-read`.impl.domain.usecase

interface FetchApiKeyUseCase {
    suspend operator fun invoke(email: String, password: String): Boolean
}