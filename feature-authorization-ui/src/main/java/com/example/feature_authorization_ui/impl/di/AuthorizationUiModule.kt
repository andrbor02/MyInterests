package com.example.feature_authorization_ui.impl.di

import com.example.feature_authorization_ui.impl.di.annotations.AuthorizationNavigation
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        AuthorizationUiModule.Bindings::class]
)
internal class AuthorizationUiModule {

    @AuthorizationNavigation
    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Module
    interface Bindings {

    }
}