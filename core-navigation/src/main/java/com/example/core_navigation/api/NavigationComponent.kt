package com.example.core_navigation.api

import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.core_utils.di_helpers.component_holder.DiComponent

interface NavigationComponent : DiComponent {

    fun routerForPeople(): RouterForPeople

    fun routerForChannels(): RouterForChannels

    fun routerForAuthorization(): RouterForAuthorization
}