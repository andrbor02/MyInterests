package com.example.feature_channels_ui.impl.presentation.stateholders.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_channels_ui.impl.presentation.model.mapper.StreamToChannelMapperImpl
import com.example.feature_channels_ui.impl.presentation.mvi.Reducer
import com.example.feature_streams.impl.domain.usecase.GetStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState
import javax.inject.Inject

class ChannelsViewModelFactory @Inject constructor(
    private val getStreamsUseCase: GetStreamsUseCase,
    private val updateStreamsUseCase: UpdateStreamsUseCase,
    private val updateTopicsUseCase: UpdateTopicsUseCase,
    private val searchStreamsUseCase: SearchStreamsUseCase,
) : ViewModelProvider.Factory {

    private val streamToChannelMapper = StreamToChannelMapperImpl()
    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChannelsViewModel(
            getStreamsUseCase = getStreamsUseCase,
            streamToChannelMapper = streamToChannelMapper,
            updateTopicsUseCase = updateTopicsUseCase,
            searchStreamsUseCase = searchStreamsUseCase,
            updateStreamsUseCase = updateStreamsUseCase,
            initialState = ChannelsScreenState.Init,
            reducer = reducer,
        ) as T
    }
}