package com.example.feature_channels_ui.impl.presentation.stateholders.all

import androidx.lifecycle.viewModelScope
import com.example.feature_channels_ui.impl.presentation.model.mapper.StreamToChannelMapper
import com.example.feature_channels_ui.impl.presentation.mvi.Event
import com.example.feature_channels_ui.impl.presentation.mvi.Reducer
import com.example.feature_channels_ui.impl.presentation.stateholders.CommonChannelsViewModel
import com.example.feature_streams.impl.domain.usecase.GetStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

internal class ChannelsViewModel(
    private val getStreamsUseCase: GetStreamsUseCase,
    private val streamToChannelMapper: StreamToChannelMapper,
    updateTopicsUseCase: UpdateTopicsUseCase,
    searchStreamsUseCase: SearchStreamsUseCase,
    updateStreamsUseCase: UpdateStreamsUseCase,
    initialState: ChannelsScreenState,
    reducer: Reducer,
) : CommonChannelsViewModel(
    updateTopicsUseCase,
    searchStreamsUseCase,
    updateStreamsUseCase,
    streamToChannelMapper,
    initialState,
    reducer
) {

    override fun loadChannels() {
        viewModelScope.launch {
            getStreamsUseCase()
                .catch { exception ->
                    sendInternalEvent(Event.Internal.LoadingError(exception.toString()))
                }
                .collect { list ->
                    sendInternalEvent(Event.Internal.ValueLoaded(
                        list.map { streamToChannelMapper(it, expandedStreams) }
                    ))
                }
        }
    }
}