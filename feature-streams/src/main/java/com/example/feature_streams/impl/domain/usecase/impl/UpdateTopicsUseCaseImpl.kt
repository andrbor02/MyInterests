package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase
import javax.inject.Inject

internal class UpdateTopicsUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : UpdateTopicsUseCase {
    override suspend fun invoke(streamId: Int): Boolean {
        return streamsRepository.updateTopics(streamId)
    }
}