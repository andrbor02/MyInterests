package com.example.feature_authorization_ui.impl.presentation.mvi.account

import com.example.feature_authorization.impl.domain.model.AccountValidationException

internal sealed class AccountEvent {
    sealed class Ui : AccountEvent() {
        object Init : Ui()
        class AccountEnter(val email: String, val password: String) : Ui()
    }

    sealed class Internal : AccountEvent() {
        class Error(val error: AccountValidationException) : Internal()
        object Success : Internal()
    }
}