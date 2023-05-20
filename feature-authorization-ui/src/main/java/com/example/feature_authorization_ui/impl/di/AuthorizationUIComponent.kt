package com.example.feature_authorization_ui.impl.di


import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_authorization_ui.api.AuthorizationUiDependencies
import com.example.feature_authorization_ui.impl.presentation.ui.AccountFragment
import com.example.feature_authorization_ui.impl.presentation.ui.AuthorizationContainerFragment
import com.example.feature_authorization_ui.impl.presentation.ui.OrganizationFragment
import dagger.Component

@Component(
    dependencies = [AuthorizationUiDependencies::class],
    modules = [AuthorizationUiModule::class]
)
internal interface AuthorizationUIComponent : DiComponent {

    fun inject(fragment: AuthorizationContainerFragment)
    fun inject(fragment: OrganizationFragment)
    fun inject(fragment: AccountFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthorizationUiDependencies): AuthorizationUIComponent
    }
}