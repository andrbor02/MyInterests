package com.example.homework_2.navigation_impl.feature_routers

import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.homework_2.navigation_impl.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RouterForAuthorizationImpl @Inject constructor(
    private val router: Router,
) : RouterForAuthorization {
    override fun navigateToMain() {
        router.newRootScreen(Screens.MainContainer())
    }
}