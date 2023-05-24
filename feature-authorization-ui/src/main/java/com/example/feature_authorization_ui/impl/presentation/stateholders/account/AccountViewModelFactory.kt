package com.example.feature_authorization_ui.impl.presentation.stateholders.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountReducer
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountScreenState
import javax.inject.Inject

class AccountViewModelFactory @Inject constructor(
    private val fetchApiKeyUseCase: FetchApiKeyUseCase,
) : ViewModelProvider.Factory {

    private val reducer = AccountReducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AccountViewModel(
            fetchApiKeyUseCase = fetchApiKeyUseCase,
            initialState = AccountScreenState.Init,
            reducer = reducer,
        ) as T
    }
}