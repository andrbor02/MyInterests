package com.example.core_navigation.impl.di

import com.example.core_navigation.api.NavigationComponent
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        NavigationModule.Bindings::class,
    ]
)
internal class NavigationModule {

    @Module
    interface Bindings {

        @Singleton
        @Binds
        fun bindNavigationComponent(navigationComponentImpl: NavigationComponentImpl): NavigationComponent
    }
}