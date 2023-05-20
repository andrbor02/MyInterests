package com.example.feature_profile_ui.impl.presentation.mvi

import com.example.core_utils.mvi_helpers.ScreenState
import com.example.core_utils_android.mvi.MviReducer

internal class Reducer :
    MviReducer<ProfileScreenState, Event, Event.Ui, Event.Internal, Command>() {
    override fun reduce(state: ProfileScreenState, event: Event): ProfileScreenState =
        when (event) {
            is Event.Ui.LoadId -> {
                sendCommandToActor(Command.LoadValue(event.id))
                ScreenState.Loading
            }

            is Event.Internal.LoadingError -> {
                ScreenState.Error(event.error)
            }

            is Event.Internal.StartLoading -> {
                ScreenState.Loading
            }

            is Event.Internal.ValueLoaded -> {
                ScreenState.Success(event.value)
            }
        }
}