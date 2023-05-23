package com.example.feature_authorization_ui.impl.presentation.mvi.organization

internal sealed class OrganizationEvent {
    sealed class Ui : OrganizationEvent() {
        object Init : Ui()
        class OrganizationEnter(val name: String) : Ui()
    }

    sealed class Internal : OrganizationEvent() {
        class Error(val error: String) : Internal()
        class Success(val isRelevant: Boolean) : Internal()
    }
}