package com.example.feature_authorization_ui.impl.presentation.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization_ui.impl.presentation.mvi.AuthorizationScreenState
import com.example.feature_authorization_ui.impl.presentation.mvi.Reducer
import javax.inject.Inject

class AuthorizationViewModelFactory @Inject constructor(
//    private val getPeopleUseCase: GetPeopleUseCase,
//    private val updatePeopleUseCase: UpdatePeopleUseCase,
//    private val searchPeopleUseCase: SearchPeopleUseCase,
) : ViewModelProvider.Factory {

    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(
//            getPeopleUseCase = getPeopleUseCase,
//            updatePeopleUseCase = updatePeopleUseCase,
//            searchPeopleUseCase = searchPeopleUseCase,
            initialState = AuthorizationScreenState.Init,
            reducer = reducer,
        ) as T
    }
}