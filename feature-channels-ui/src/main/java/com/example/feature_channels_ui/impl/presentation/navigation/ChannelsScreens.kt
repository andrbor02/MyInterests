package com.example.feature_channels_ui.impl.presentation.navigation

import com.example.feature_channels_ui.impl.presentation.ui.CreateChannelFragment
import com.example.feature_channels_ui.impl.presentation.ui.SearchChannelsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object ChannelsScreens {
    fun CreateChannel() =
        FragmentScreen(clearContainer = false) {
            CreateChannelFragment()
        }

    fun SearchChannels() = FragmentScreen(clearContainer = false) {
        SearchChannelsFragment()
    }
}