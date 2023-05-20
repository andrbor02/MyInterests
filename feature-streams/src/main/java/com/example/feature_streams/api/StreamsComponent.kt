package com.example.feature_streams.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_streams.impl.domain.usecase.CreateStreamUseCase
import com.example.feature_streams.impl.domain.usecase.GetStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.GetSubscribedStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.GetTopicsUseCase
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase

interface StreamsComponent : DiComponent {
    fun getStreamsUseCase(): GetStreamsUseCase

    fun getSubscribedStreamsUseCase(): GetSubscribedStreamsUseCase

    fun getTopicsUseCase(): GetTopicsUseCase

    fun updateStreamsUseCase(): UpdateStreamsUseCase

    fun updateTopicsUseCase(): UpdateTopicsUseCase

    fun searchStreamsUseCase(): SearchStreamsUseCase

    fun createStreamUseCase(): CreateStreamUseCase
}