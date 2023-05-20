package com.example.feature_authorization_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_authorization_ui.api.AuthorizationUiDependencies

internal object AuthorizationUiComponentHolder :
    FeatureComponentHolder<AuthorizationUIComponent>() {
    override fun build(): AuthorizationUIComponent {
        return DaggerAuthorizationUIComponent.factory().create(
            AuthorizationUiDependencies.Impl()
        )
    }
}