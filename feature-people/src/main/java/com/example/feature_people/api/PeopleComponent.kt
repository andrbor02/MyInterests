package com.example.feature_people.api

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase

interface PeopleComponent : DiComponent {

    fun getPeopleUseCase(): GetPeopleUseCase

    fun updatePeopleUseCase(): UpdatePeopleUseCase

    fun searchPeopleUseCase(): SearchPeopleUseCase
}