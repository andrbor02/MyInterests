package com.example.feature_authorization_read.impl.domain.usecase

interface GetApiKeyUseCase {
    suspend operator fun invoke(): String
}