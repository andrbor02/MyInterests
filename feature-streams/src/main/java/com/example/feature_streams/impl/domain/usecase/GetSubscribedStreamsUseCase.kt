package com.example.feature_streams.impl.domain.usecase

import com.example.feature_streams.impl.domain.model.StreamModel
import kotlinx.coroutines.flow.Flow


interface GetSubscribedStreamsUseCase {
    operator fun invoke(): Flow<List<StreamModel>>
}