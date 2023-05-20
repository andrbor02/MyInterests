package com.example.feature_profile_ui.impl.di

import dagger.Module

@Module(
    includes = [
        ProfileUiModule.Bindings::class]
)
internal class ProfileUiModule {

    @Module
    interface Bindings {

    }
}