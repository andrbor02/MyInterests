package com.example.feature_people.impl.di

import com.example.feature_authorization_read.api.AuthorizationComponent
import com.example.`feature_authorization-read`.impl.di.AuthorizationModule
import com.example.feature_people.api.PeopleDependencies
import dagger.Component

@Component(
    dependencies = [PeopleDependencies::class],
    modules = [AuthorizationModule::class]
)
internal interface AuthorizationComponentImpl : AuthorizationComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: PeopleDependencies): AuthorizationComponent
    }
}