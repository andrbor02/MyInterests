package com.example.feature_authorization_write.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_authorization_write.api.AuthorizationWriteComponent
import com.example.feature_authorization_write.api.AuthorizationWriteDependencies

object AuthorizationComponentHolder : FeatureComponentHolder<AuthorizationWriteComponent>() {
    override fun build(): AuthorizationWriteComponent =
        DaggerAuthorizationComponentImpl.factory().create(
            AuthorizationWriteDependencies.Impl()
        )
}