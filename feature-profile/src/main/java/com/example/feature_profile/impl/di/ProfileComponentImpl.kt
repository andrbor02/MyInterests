package com.example.feature_profile.impl.di

import com.example.feature_profile.api.ProfileComponent
import com.example.feature_profile.api.ProfileDependencies
import dagger.Component

@Component(
    dependencies = [ProfileDependencies::class],
    modules = [ProfileModule::class]
)
internal interface ProfileComponentImpl : ProfileComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ProfileDependencies): ProfileComponent
    }
}