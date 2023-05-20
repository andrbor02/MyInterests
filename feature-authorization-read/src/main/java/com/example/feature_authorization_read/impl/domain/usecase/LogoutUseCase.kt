package com.example.feature_authorization_read.impl.domain.usecase

interface LogoutUseCase {
    suspend operator fun invoke(): Boolean
}