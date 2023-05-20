package com.example.feature_channels_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_channels_ui.api.ChannelsUiDependencies

internal object ChannelsUiComponentHolder : FeatureComponentHolder<ChannelsUiComponent>() {
    override fun build(): ChannelsUiComponent {
        return DaggerChannelsUiComponent.factory().create(
            ChannelsUiDependencies.Impl()
        )
    }
}