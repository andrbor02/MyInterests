package com.example.homework_2.navigation_impl.feature_routers

import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.homework_2.navigation_impl.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RouterForPeopleImpl @Inject constructor(
    private val router: Router,
) : RouterForPeople {
    override fun navigateToProfile(userId: Int) {
        router.navigateTo(Screens.Profile(userId))
    }
}