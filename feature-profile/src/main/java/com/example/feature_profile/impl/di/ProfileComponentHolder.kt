package com.example.feature_profile.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_profile.api.ProfileComponent
import com.example.feature_profile.api.ProfileDependencies

object ProfileComponentHolder : FeatureComponentHolder<ProfileComponent>() {
    override fun build(): ProfileComponent =
        DaggerProfileComponentImpl.factory().create(
            ProfileDependencies.Impl()
        )
}