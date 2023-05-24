package com.example.core_network.impl.di

import com.example.core_network.api.NetworkComponent
import com.example.core_network.api.NetworkDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NetworkDependencies::class],
    modules = [NetworkModule::class]
)
internal interface NetworkComponentImpl : NetworkComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NetworkDependencies): NetworkComponentImpl
    }
}