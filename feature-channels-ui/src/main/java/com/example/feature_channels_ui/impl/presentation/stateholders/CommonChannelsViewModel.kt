package com.example.feature_channels_ui.impl.presentation.stateholders

import androidx.lifecycle.viewModelScope
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_channels_ui.impl.presentation.model.mapper.StreamToChannelMapper
import com.example.feature_channels_ui.impl.presentation.mvi.Command
import com.example.feature_channels_ui.impl.presentation.mvi.Event
import com.example.feature_channels_ui.impl.presentation.mvi.Reducer
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

internal abstract class CommonChannelsViewModel(
    private val updateTopicsUseCase: UpdateTopicsUseCase,
    private val searchStreamsUseCase: SearchStreamsUseCase,
    private val updateStreamsUseCase: UpdateStreamsUseCase,
    private val streamToChannelMapper: StreamToChannelMapper,
    initialState: ChannelsScreenState,
    reducer: Reducer,
) : MviViewModel<ChannelsScreenState, Event, Event.Ui, Event.Internal, Command>(
    initialState,
    reducer
) {

    private val _searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        subscribeToSearchQueryChanges()
    }

    override suspend fun actor(command: Command) {
        when (command) {
            is Command.LoadList -> loadChannels()
            is Command.SearchValue -> _searchQueryState.emit(command.query)
            is Command.LoadTopics -> loadTopics(command.streamId)
            is Command.UpdateList -> updateChannels()
            is Command.RegisterExpand -> registerExpand(command.streamId)
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeToSearchQueryChanges() {
        _searchQueryState
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(search(it)) } }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private suspend fun search(query: String) {
        viewModelScope.launch {
            val result = searchStreamsUseCase(query)

            if (result.isEmpty()) {
                sendInternalEvent(Event.Internal.SearchError(SEARCH_ERROR))
            } else {
                sendInternalEvent(
                    Event.Internal.ValueLoaded(
                        result.map { streamToChannelMapper(it, emptyList()) })
                )
            }
        }
    }

    protected abstract fun loadChannels()

    private fun updateChannels() {
        viewModelScope.launch(Dispatchers.IO) {
            val isUpdateSuccessful = updateStreamsUseCase()

            if (isUpdateSuccessful) {
                sendInternalEvent(Event.Internal.UpdateSuccess)
            } else {
                sendInternalEvent(Event.Internal.LoadingError(LOADING_ERROR))
            }
        }
    }

    private fun loadTopics(streamId: Int) {
        viewModelScope.launch {
            val topics = runCatchingNonCancellation {
                updateTopicsUseCase(streamId)
            }

            topics.fold(
                onSuccess = { topicsLoaded ->
                    if (topicsLoaded) {
                        sendInternalEvent(Event.Internal.TopicsLoaded)
                    } else {
                        sendInternalEvent(Event.Internal.TopicsError)
                    }
                },
                onFailure = { sendInternalEvent(Event.Internal.TopicsError) }
            )
        }
    }

    protected val expandedStreams = mutableListOf<Int>()
    private fun registerExpand(streamId: Int) {
        if (expandedStreams.contains(streamId)) {
            expandedStreams.remove(streamId)
        } else {
            expandedStreams.add(streamId)
        }
    }

    private companion object {
        private const val SEARCH_ERROR = "Can't find it"
        private const val LOADING_ERROR = "Can't load list"
    }
}