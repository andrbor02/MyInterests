package com.example.feature_channels_ui.impl.presentation.mvi

import com.example.core_utils_android.mvi.MviReducer
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState

internal class Reducer :
    MviReducer<ChannelsScreenState, Event, Event.Ui, Event.Internal, Command>() {
    override fun reduce(state: ChannelsScreenState, event: Event): ChannelsScreenState =
        when (event) {
            is Event.Ui.Init -> {
                if (state is ChannelsScreenState.Init) {
                    sendCommandToActor(Command.LoadList)
                    sendCommandToActor(Command.UpdateList)
                    ChannelsScreenState.Loading
                } else {
                    state
                }
            }

            is Event.Ui.Search -> {
                sendCommandToActor(Command.SearchValue(event.query))
                ChannelsScreenState.Loading
            }

            is Event.Ui.ReloadClick -> {
                sendCommandToActor(Command.LoadList)
                sendCommandToActor(Command.UpdateList)
                ChannelsScreenState.Loading
            }

            is Event.Ui.LoadTopics -> {
                if (state is ChannelsScreenState.Success) {
                    val topicsNotLoaded =
                        state.data.find { it.id == event.streamId }?.topics?.isEmpty()
                    if (topicsNotLoaded != null && topicsNotLoaded) {
                        sendCommandToActor(Command.LoadTopics(event.streamId))
                    }
                }
                sendCommandToActor(Command.RegisterExpand(event.streamId))
                state
            }

            Event.Ui.UpdateList -> {
                sendCommandToActor(Command.UpdateList)
                state
            }

            is Event.Internal.LoadingError -> {
                if (state is ChannelsScreenState.Success && state.data.isNotEmpty()) {
                    state
                } else {
                    ChannelsScreenState.ListError(event.error)
                }
            }

            is Event.Internal.ValueLoaded -> {
                if (event.value.isEmpty() && state is ChannelsScreenState.ListError) {
                    state
                } else {
                    ChannelsScreenState.Success(event.value)
                }
            }

            is Event.Internal.TopicsLoaded -> {
                state
            }

            is Event.Internal.TopicsError -> {
                state
            }

            Event.Internal.UpdateSuccess -> {
                state
            }

            is Event.Internal.SearchError -> {
                ChannelsScreenState.SearchError(event.error)
            }
        }
}