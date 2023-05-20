package com.example.feature_profile_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_profile_ui.api.ProfileUiDependencies

internal object ProfileUiComponentHolder : FeatureComponentHolder<ProfileUiComponent>() {
    override fun build(): ProfileUiComponent {
        return DaggerProfileUiComponent.factory().create(
            ProfileUiDependencies.Impl()
        )
    }
}