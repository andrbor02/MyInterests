package com.example.core_context_provider.impl.di

import com.example.core_context_provider.api.ContextProviderComponent
import com.example.core_context_provider.api.ContextProviderDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ContextProviderDependencies::class],
)
interface ContextProviderComponentImpl : ContextProviderComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ContextProviderDependencies): ContextProviderComponentImpl
    }
}