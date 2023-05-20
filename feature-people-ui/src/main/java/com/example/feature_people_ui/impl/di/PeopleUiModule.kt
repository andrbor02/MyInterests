package com.example.feature_people_ui.impl.di

import dagger.Module

@Module(
    includes = [
        PeopleUiModule.Bindings::class]
)
internal class PeopleUiModule {

    @Module
    interface Bindings {

    }
}