package com.example.feature_streams.impl.domain.usecase

import com.example.feature_streams.impl.domain.model.TopicModel

interface GetTopicsUseCase {
    suspend operator fun invoke(streamId: Int): List<TopicModel>
}