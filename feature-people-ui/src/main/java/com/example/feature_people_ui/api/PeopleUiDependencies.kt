package com.example.feature_people_ui.api

import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.feature_people.impl.di.PeopleComponentHolder
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase

internal interface PeopleUiDependencies {
    fun router(): RouterForPeople

    fun getPeopleUseCase(): GetPeopleUseCase

    fun updatePeopleUseCase(): UpdatePeopleUseCase

    fun getSearchPeopleUseCase(): SearchPeopleUseCase

    class Impl : PeopleUiDependencies {

        override fun router(): RouterForPeople {
            return NavigationComponentHolder.get().routerForPeople()
        }

        override fun getPeopleUseCase(): GetPeopleUseCase {
            return PeopleComponentHolder.get().getPeopleUseCase()
        }

        override fun updatePeopleUseCase(): UpdatePeopleUseCase {
            return PeopleComponentHolder.get().updatePeopleUseCase()
        }

        override fun getSearchPeopleUseCase(): SearchPeopleUseCase {
            return PeopleComponentHolder.get().searchPeopleUseCase()
        }
    }
}