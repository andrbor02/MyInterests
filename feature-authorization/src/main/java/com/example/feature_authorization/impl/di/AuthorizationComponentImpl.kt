package com.example.feature_authorization.impl.di

import com.example.feature_authorization.api.AuthorizationComponent
import com.example.feature_authorization.api.AuthorizationDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AuthorizationDependencies::class],
    modules = [AuthorizationModule::class]
)
internal interface AuthorizationComponentImpl : AuthorizationComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthorizationDependencies): AuthorizationComponent
    }
}