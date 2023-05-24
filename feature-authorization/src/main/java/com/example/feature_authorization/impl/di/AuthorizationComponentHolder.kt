package com.example.feature_authorization.impl.di

import com.example.core_utils.di_helpers.component_holder.DataComponentHolder
import com.example.feature_authorization.api.AuthorizationComponent
import com.example.feature_authorization.api.AuthorizationDependencies

object AuthorizationComponentHolder : DataComponentHolder<AuthorizationComponent>() {
    override fun build(): AuthorizationComponent =
        DaggerAuthorizationComponentImpl.factory().create(
            AuthorizationDependencies.Impl()
        )
}