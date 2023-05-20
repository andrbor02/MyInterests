package com.example.feature_channels_ui.impl.di

import com.example.feature_channels_ui.impl.di.annotations.ChannelsNavigation
import com.example.feature_channels_ui.impl.presentation.model.mapper.StreamToChannelMapper
import com.example.feature_channels_ui.impl.presentation.model.mapper.StreamToChannelMapperImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ChannelsUiModule.Bindings::class]
)
internal class ChannelsUiModule {

    @Provides
    @ChannelsNavigation
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Module
    interface Bindings {

        @Binds
        fun bindStreamToChannelMapper(streamToChannelMapperImpl: StreamToChannelMapperImpl): StreamToChannelMapper
    }
}