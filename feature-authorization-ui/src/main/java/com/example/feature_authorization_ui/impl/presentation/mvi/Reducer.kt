package com.example.feature_authorization_ui.impl.presentation.mvi

import com.example.core_utils_android.mvi.MviReducer

internal class Reducer :
    MviReducer<AuthorizationScreenState, Event, Event.Ui, Event.Internal, Command>() {
    override fun reduce(state: AuthorizationScreenState, event: Event): AuthorizationScreenState =
        when (event) {
            is Event.Ui.Init -> {
                if (state is AuthorizationScreenState.Init) {
//                sendCommandToActor(Command.LoadList)
//                sendCommandToActor(Command.UpdateList)
                    AuthorizationScreenState.Init
                } else {
                    state
                }
            }

//        is Event.Ui.Search -> {
//            sendCommandToActor(Command.Search(event.query))
//            state
//        }
//
//        Event.Ui.UpdateList -> {
//            sendCommandToActor(Command.UpdateList)
//            AuthorizationScreenState.Loading
//        }
//
//        is Event.Ui.ReloadListClick -> {
//            sendCommandToActor(Command.LoadList)
//            sendCommandToActor(Command.UpdateList)
//            AuthorizationScreenState.Loading
//        }
//
//        is Event.Internal.LoadingError -> {
//            AuthorizationScreenState.ListError(event.error)
//        }
//
//        is Event.Internal.SearchError -> {
//            AuthorizationScreenState.SearchError(event.error)
//        }
//
//        is Event.Internal.LoadingSuccess -> {
//            if (state is AuthorizationScreenState.Success && state.data.isNotEmpty()) {
//                state
//            } else {
//                AuthorizationScreenState.Success(event.value)
//            }
//        }
//
//        Event.Internal.UpdateSuccess -> {
//            state
//        }
//
//        Event.Internal.UpdateError -> {
//            state
//        }
//
//        Event.Internal.StartLoading -> {
//            AuthorizationScreenState.Loading
//        }
            is Event.Ui.OrganizationEnter -> TODO()
        }
}