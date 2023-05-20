package com.example.feature_authorization_ui.api

import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForPeople

internal interface AuthorizationUiDependencies {
    fun router(): RouterForPeople

//    fun getPeopleUseCase(): GetPeopleUseCase
//
//    fun updatePeopleUseCase(): UpdatePeopleUseCase
//
//    fun getSearchPeopleUseCase(): SearchPeopleUseCase

    class Impl : AuthorizationUiDependencies {

        override fun router(): RouterForPeople {
            return NavigationComponentHolder.get().routerForPeople()
        }

//        override fun getPeopleUseCase(): GetPeopleUseCase {
//            return AuthorizationComponentHolder.get().getPeopleUseCase()
//        }
//
//        override fun updatePeopleUseCase(): UpdatePeopleUseCase {
//            return AuthorizationComponentHolder.get().updatePeopleUseCase()
//        }
//
//        override fun getSearchPeopleUseCase(): SearchPeopleUseCase {
//            return AuthorizationComponentHolder.get().searchPeopleUseCase()
//        }
    }
}