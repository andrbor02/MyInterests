package com.example.feature_authorization_ui.impl.presentation.mvi.account

import com.example.core_utils_android.mvi.MviReducer

internal class AccountReducer :
    MviReducer<AccountScreenState, AccountEvent, AccountEvent.Ui, AccountEvent.Internal, AccountCommand>() {
    override fun reduce(
        state: AccountScreenState,
        event: AccountEvent,
    ): AccountScreenState =
        when (event) {
            is AccountEvent.Ui.Init -> {
                state
            }

            is AccountEvent.Ui.AccountEnter -> {
                sendCommandToActor(AccountCommand.FetchApiKey(event.email, event.password))
                AccountScreenState.Loading
            }

            is AccountEvent.Internal.Error -> {
                AccountScreenState.Error(event.error)
            }

            is AccountEvent.Internal.Success -> {
                AccountScreenState.Success
            }
        }
}