package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.model.TopicModel
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.GetTopicsUseCase
import javax.inject.Inject

internal class GetTopicsUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : GetTopicsUseCase {
    override suspend fun invoke(streamId: Int): List<TopicModel> {
        return streamsRepository.getTopics(streamId)
    }
}