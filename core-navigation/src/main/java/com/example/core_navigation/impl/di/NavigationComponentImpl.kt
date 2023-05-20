package com.example.core_navigation.impl.di

import com.example.core_navigation.api.NavigationComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    dependencies = [NavigationComponent::class],
    modules = [NavigationModule::class]
)
interface NavigationComponentImpl : NavigationComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NavigationComponent): NavigationComponentImpl
    }
}