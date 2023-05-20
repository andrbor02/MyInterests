package com.example.feature_people_ui.impl.presentation.mvi

import com.example.feature_people.impl.domain.model.UserModel

internal sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        class Search(val query: String) : Ui()
        object UpdateList : Ui()
        object ReloadListClick : Ui()
    }

    sealed class Internal : Event() {
        class LoadingSuccess(val value: List<UserModel>) : Internal()
        object StartLoading : Internal()
        object UpdateSuccess : Internal()
        object UpdateError : Internal()
        class LoadingError(val error: String) : Internal()
        class SearchError(val error: String) : Internal()
    }
}