package com.example.feature_authorization_ui.impl.presentation.mvi

internal sealed class Command {
    class ConnectToOrganization(val organization: String): Command()
//    class FetchApiKey(val query: String) : Command()
}