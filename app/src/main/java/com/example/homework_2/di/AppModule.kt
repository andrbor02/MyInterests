package com.example.homework_2.di

import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.homework_2.navigation_impl.feature_routers.RouterForAuthorizationImpl
import com.example.homework_2.navigation_impl.feature_routers.RouterForChannelsImpl
import com.example.homework_2.navigation_impl.feature_routers.RouterForPeopleImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        AppModule.NavigationModule::class,
        AppModule.NavigationModule.Bindings::class,
    ]
)
class AppModule {

    @Module
    class NavigationModule {
        @Singleton
        @Provides
        fun provideCicerone(): Cicerone<Router> {
            return Cicerone.create()
        }

        @Singleton
        @Provides
        fun provideRouter(cicerone: Cicerone<Router>): Router {
            return cicerone.router
        }

        @Singleton
        @Provides
        fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }

        @Module
        interface Bindings {
            @Singleton
            @Binds
            fun bindRouterForPeople(routerForPeopleImpl: RouterForPeopleImpl): RouterForPeople

            @Singleton
            @Binds
            fun bindRouterForChannels(routerForChannelsImpl: RouterForChannelsImpl): RouterForChannels

            @Singleton
            @Binds
            fun bindRouterForAuthorization(routerForAuthorizationImpl: RouterForAuthorizationImpl): RouterForAuthorization
        }
    }
}