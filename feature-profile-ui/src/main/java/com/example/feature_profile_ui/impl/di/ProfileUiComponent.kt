package com.example.feature_profile_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_profile_ui.api.ProfileUiDependencies
import com.example.feature_profile_ui.impl.presentation.ui.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProfileUiDependencies::class],
    modules = [ProfileUiModule::class]
)
internal interface ProfileUiComponent : DiComponent {

    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ProfileUiDependencies): ProfileUiComponent
    }
}