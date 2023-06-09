package com.example.homework_2.navigation_impl.feature_routers

import com.example.core_navigation.impl.routers.RouterForProfile
import com.example.homework_2.navigation_impl.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RouterForProfileImpl @Inject constructor(
    private val router: Router,
) : RouterForProfile {
    override fun navigateToMain() {
        router.navigateTo(Screens.MainContainer())
    }
}