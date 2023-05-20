package com.example.feature_streams.impl.domain.usecase

import com.example.feature_streams.impl.domain.model.StreamModel

interface SearchStreamsUseCase {
    suspend operator fun invoke(query: String): List<StreamModel>
}