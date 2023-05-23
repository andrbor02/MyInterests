package com.example.core_data.impl.di

import com.example.core_data.api.DataComponent
import com.example.core_data.api.DataDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [DataDependencies::class],
    modules = [DataModule::class]
)
internal interface DataComponentImpl : DataComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DataDependencies): DataComponentImpl
    }
}