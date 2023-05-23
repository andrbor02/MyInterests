package com.example.feature_authorization_ui.impl.presentation.mvi.account

internal sealed class AccountCommand {
    class FetchApiKey(val email: String, val password: String) : AccountCommand()
}