package com.example.feature_channels_ui.api

import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.feature_streams.impl.di.StreamsComponentHolder
import com.example.feature_streams.impl.domain.usecase.CreateStreamUseCase
import com.example.feature_streams.impl.domain.usecase.GetStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.GetSubscribedStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase

internal interface ChannelsUiDependencies {

    fun router(): RouterForChannels

    fun getStreamsUseCase(): GetStreamsUseCase

    fun getSubscribedStreamsUseCase(): GetSubscribedStreamsUseCase

    fun updateTopicsUseCase(): UpdateTopicsUseCase

    fun searchStreamsUseCase(): SearchStreamsUseCase

    fun updateStreamsUseCase(): UpdateStreamsUseCase

    fun createStreamUseCase(): CreateStreamUseCase

    class Impl : ChannelsUiDependencies {

        override fun router(): RouterForChannels {
            return NavigationComponentHolder.get().routerForChannels()
        }

        override fun getStreamsUseCase(): GetStreamsUseCase {
            return StreamsComponentHolder.get().getStreamsUseCase()
        }

        override fun getSubscribedStreamsUseCase(): GetSubscribedStreamsUseCase {
            return StreamsComponentHolder.get().getSubscribedStreamsUseCase()
        }

        override fun updateTopicsUseCase(): UpdateTopicsUseCase {
            return StreamsComponentHolder.get().updateTopicsUseCase()
        }

        override fun searchStreamsUseCase(): SearchStreamsUseCase {
            return StreamsComponentHolder.get().searchStreamsUseCase()
        }

        override fun updateStreamsUseCase(): UpdateStreamsUseCase {
            return StreamsComponentHolder.get().updateStreamsUseCase()
        }

        override fun createStreamUseCase(): CreateStreamUseCase {
            return StreamsComponentHolder.get().createStreamUseCase()
        }
    }
}