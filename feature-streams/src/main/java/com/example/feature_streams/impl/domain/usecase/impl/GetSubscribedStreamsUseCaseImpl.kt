package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.model.StreamModel
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.GetSubscribedStreamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetSubscribedStreamsUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : GetSubscribedStreamsUseCase {
    override operator fun invoke(): Flow<List<StreamModel>> {
        return streamsRepository.getSubscribedStreams()
    }
}