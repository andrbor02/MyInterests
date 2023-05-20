package com.example.feature_people.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.`feature_authorization-read`.api.AuthorizationComponent
import com.example.feature_people.api.PeopleDependencies

object AuthorizationComponentHolder : FeatureComponentHolder<AuthorizationComponent>() {
    override fun build(): AuthorizationComponent =
        DaggerAuthorizationComponentImpl.factory().create(
            PeopleDependencies.Impl()
        )
}