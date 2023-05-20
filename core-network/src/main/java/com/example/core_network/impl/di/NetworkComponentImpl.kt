package com.example.core_network.impl.di

import com.example.core_network.api.NetworkComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
internal interface NetworkComponentImpl : NetworkComponent {

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponentImpl
    }
}