package com.example.feature_streams.impl.domain.usecase

import com.example.feature_streams.impl.domain.model.StreamInfo

interface CreateStreamUseCase {
    suspend operator fun invoke(streamInfo: StreamInfo): Boolean
}