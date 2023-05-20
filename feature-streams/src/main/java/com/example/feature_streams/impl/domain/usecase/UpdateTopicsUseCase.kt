package com.example.feature_streams.impl.domain.usecase

interface UpdateTopicsUseCase {
    suspend operator fun invoke(streamId: Int): Boolean
}