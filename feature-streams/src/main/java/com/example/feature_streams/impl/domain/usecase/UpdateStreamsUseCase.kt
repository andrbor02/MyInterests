package com.example.feature_streams.impl.domain.usecase

interface UpdateStreamsUseCase {
    suspend operator fun invoke(): Boolean
}