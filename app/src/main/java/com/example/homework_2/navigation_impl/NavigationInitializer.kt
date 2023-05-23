package com.example.homework_2.navigation_impl

import com.example.core_navigation.api.NavigationComponent
import com.example.core_navigation.impl.di.DaggerNavigationComponentImpl
import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.core_navigation.impl.routers.RouterForProfile
import javax.inject.Inject

class NavigationInitializer @Inject constructor(
    private val routerForPeople: RouterForPeople,
    private val routerForChannels: RouterForChannels,
    private val routerForAuthorization: RouterForAuthorization,
    private val routerForProfile: RouterForProfile,
) {
    fun init() {
        NavigationComponentHolder.set(
            DaggerNavigationComponentImpl.factory().create(
                object : NavigationComponent {
                    override fun routerForPeople(): RouterForPeople {
                        return routerForPeople
                    }

                    override fun routerForChannels(): RouterForChannels {
                        return routerForChannels
                    }

                    override fun routerForAuthorization(): RouterForAuthorization {
                        return routerForAuthorization
                    }

                    override fun routerForProfile(): RouterForProfile {
                        return routerForProfile
                    }
                }
            )
        )
    }
}