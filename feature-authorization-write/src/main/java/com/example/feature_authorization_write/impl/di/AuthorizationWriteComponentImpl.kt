package com.example.feature_authorization_write.impl.di

import com.example.feature_authorization_write.api.AuthorizationWriteComponent
import com.example.feature_authorization_write.api.AuthorizationWriteDependencies
import dagger.Component

@Component(
    dependencies = [AuthorizationWriteDependencies::class],
    modules = [AuthorizationModule::class]
)
internal interface AuthorizationWriteComponentImpl : AuthorizationWriteComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthorizationWriteDependencies): AuthorizationWriteComponent
    }
}