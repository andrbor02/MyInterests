package com.example.feature_authorization_ui.impl.presentation.mvi

internal sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        class OrganizationEnter(val query: String) : Ui()
//        object UpdateList : Ui()
//        object ReloadListClick : Ui()
    }

    sealed class Internal : Event() {
//        class LoadingSuccess(val value: List<UserModel>) : Internal()
//        object StartLoading : Internal()
//        object UpdateSuccess : Internal()
//        object UpdateError : Internal()
//        class LoadingError(val error: String) : Internal()
//        class SearchError(val error: String) : Internal()
    }
}