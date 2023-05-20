package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.model.StreamInfo
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.CreateStreamUseCase
import javax.inject.Inject

internal class CreateStreamUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : CreateStreamUseCase {
    override suspend fun invoke(streamInfo: StreamInfo): Boolean {
        return streamsRepository.createStream(streamInfo)
    }
}