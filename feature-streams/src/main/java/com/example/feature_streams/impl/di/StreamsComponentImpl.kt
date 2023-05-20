package com.example.feature_streams.impl.di

import com.example.feature_streams.api.StreamsComponent
import com.example.feature_streams.api.StreamsDependencies
import dagger.Component

@Component(
    dependencies = [StreamsDependencies::class],
    modules = [StreamsModule::class]
)
internal interface StreamsComponentImpl : StreamsComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: StreamsDependencies): StreamsComponent
    }
}