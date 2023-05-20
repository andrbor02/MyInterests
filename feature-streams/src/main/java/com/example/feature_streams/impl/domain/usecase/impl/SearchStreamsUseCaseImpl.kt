package com.example.feature_streams.impl.domain.usecase.impl

import com.example.feature_streams.impl.domain.model.StreamModel
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import javax.inject.Inject

internal class SearchStreamsUseCaseImpl @Inject constructor(
    private val streamsRepository: StreamsRepository,
) : SearchStreamsUseCase {
    override suspend operator fun invoke(query: String): List<StreamModel> {
        return streamsRepository.searchStreams(query)
    }
}