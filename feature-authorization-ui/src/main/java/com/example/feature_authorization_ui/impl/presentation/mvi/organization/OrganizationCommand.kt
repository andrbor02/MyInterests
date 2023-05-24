package com.example.feature_authorization_ui.impl.presentation.mvi.organization

internal sealed class OrganizationCommand {
    class ConnectToOrganization(val organization: String) : OrganizationCommand()
}