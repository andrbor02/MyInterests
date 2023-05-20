package com.example.feature_profile_ui.impl.presentation.mvi

import com.example.feature_profile.impl.domain.model.ProfileModel


internal sealed class Event {
    sealed class Ui : Event() {
        class LoadId(val id: Int) : Ui()
    }

    sealed class Internal : Event() {
        object StartLoading : Internal()
        class ValueLoaded(val value: ProfileModel) : Internal()
        class LoadingError(val error: String) : Internal()
    }

}