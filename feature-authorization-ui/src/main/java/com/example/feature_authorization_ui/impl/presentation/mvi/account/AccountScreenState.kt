package com.example.feature_authorization_ui.impl.presentation.mvi.account

import com.example.feature_authorization.impl.domain.model.AccountValidationException

sealed class AccountScreenState {
    object Init : AccountScreenState()
    object Loading : AccountScreenState()
    data class Error(val accountValidationException: AccountValidationException) : AccountScreenState()
    object Success : AccountScreenState()
}