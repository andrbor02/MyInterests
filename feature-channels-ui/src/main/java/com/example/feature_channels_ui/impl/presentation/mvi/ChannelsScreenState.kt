package com.example.homework_2.feature_channels.presentation

import com.example.feature_channels_ui.impl.presentation.model.ChannelModel

internal sealed class ChannelsScreenState {
    object Init : ChannelsScreenState()
    object Loading : ChannelsScreenState()
    data class ListError(val errorMessage: String) : ChannelsScreenState()
    data class SearchError(val errorMessage: String) : ChannelsScreenState()
    data class Success(val data: List<ChannelModel>) : ChannelsScreenState()
}