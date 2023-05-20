package com.example.feature_channels_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_channels_ui.api.ChannelsUiDependencies
import com.example.feature_channels_ui.impl.presentation.ui.ChannelsFragment
import com.example.feature_channels_ui.impl.presentation.ui.ChannelsTabsFragment
import com.example.feature_channels_ui.impl.presentation.ui.CreateChannelFragment
import com.example.feature_channels_ui.impl.presentation.ui.SearchChannelsFragment
import dagger.Component

@Component(
    dependencies = [ChannelsUiDependencies::class],
    modules = [ChannelsUiModule::class]
)
internal interface ChannelsUiComponent : DiComponent {

    fun inject(fragment: ChannelsFragment)
    fun inject(fragment: ChannelsTabsFragment)
    fun inject(fragment: SearchChannelsFragment)
    fun inject(fragment: CreateChannelFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChannelsUiDependencies): ChannelsUiComponent
    }
}