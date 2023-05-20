package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import javax.inject.Inject

internal class UpdateStreamsUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : UpdateStreamsUseCase {
    override suspend fun invoke(): Boolean {
        return streamsRepository.updateStreams()
    }
}