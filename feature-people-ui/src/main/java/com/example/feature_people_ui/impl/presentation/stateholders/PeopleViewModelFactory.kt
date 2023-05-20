package com.example.feature_people_ui.impl.presentation.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase
import com.example.feature_people_ui.impl.presentation.mvi.PeopleScreenState
import com.example.feature_people_ui.impl.presentation.mvi.Reducer
import javax.inject.Inject

class PeopleViewModelFactory @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val updatePeopleUseCase: UpdatePeopleUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase,
) : ViewModelProvider.Factory {

    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleViewModel(
            getPeopleUseCase = getPeopleUseCase,
            updatePeopleUseCase = updatePeopleUseCase,
            searchPeopleUseCase = searchPeopleUseCase,
            initialState = PeopleScreenState.Init,
            reducer = reducer,
        ) as T
    }
}