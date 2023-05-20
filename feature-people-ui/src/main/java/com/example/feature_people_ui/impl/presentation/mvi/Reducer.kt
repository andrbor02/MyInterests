package com.example.feature_people_ui.impl.presentation.mvi

import com.example.core_utils_android.mvi.MviReducer

internal class Reducer : MviReducer<PeopleScreenState, Event, Event.Ui, Event.Internal, Command>() {
    override fun reduce(state: PeopleScreenState, event: Event): PeopleScreenState = when (event) {
        is Event.Ui.Init -> {
            if (state is PeopleScreenState.Init) {
                sendCommandToActor(Command.LoadList)
                sendCommandToActor(Command.UpdateList)
                PeopleScreenState.Loading
            } else {
                state
            }
        }

        is Event.Ui.Search -> {
            sendCommandToActor(Command.Search(event.query))
            state
        }

        Event.Ui.UpdateList -> {
            sendCommandToActor(Command.UpdateList)
            PeopleScreenState.Loading
        }

        is Event.Ui.ReloadListClick -> {
            sendCommandToActor(Command.LoadList)
            sendCommandToActor(Command.UpdateList)
            PeopleScreenState.Loading
        }

        is Event.Internal.LoadingError -> {
            PeopleScreenState.ListError(event.error)
        }

        is Event.Internal.SearchError -> {
            PeopleScreenState.SearchError(event.error)
        }

        is Event.Internal.LoadingSuccess -> {
            if (state is PeopleScreenState.Success && state.data.isNotEmpty()) {
                state
            } else {
                PeopleScreenState.Success(event.value)
            }
        }

        Event.Internal.UpdateSuccess -> {
            state
        }

        Event.Internal.UpdateError -> {
            state
        }

        Event.Internal.StartLoading -> {
            PeopleScreenState.Loading
        }
    }
}