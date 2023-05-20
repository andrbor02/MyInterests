package com.example.homework_2.navigation_impl

import com.example.feature_authorization_ui.impl.presentation.ui.AuthorizationContainerFragment
import com.example.feature_channels_ui.impl.presentation.ui.ChannelsTabsFragment
import com.example.feature_chat_ui.impl.presentation.ui.ChatFragment
import com.example.feature_people_ui.impl.presentation.ui.PeopleFragment
import com.example.feature_profile_ui.impl.presentation.ui.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun MainContainer() = FragmentScreen { MainContainerFragment() }
    fun AuthorizationContainer() = FragmentScreen { AuthorizationContainerFragment() }

    fun Channels() = FragmentScreen { ChannelsTabsFragment() }
    fun People() = FragmentScreen { PeopleFragment() }
    fun Profile(userId: Int? = null) =
        FragmentScreen(clearContainer = false) {
            ProfileFragment.newInstance(userId)
        }

    fun Chat(
        streamId: Int,
        streamName: String,
        topicName: String? = null,
    ) = FragmentScreen(clearContainer = false) {
        ChatFragment.newInstance(
            streamId,
            streamName,
            topicName,
        )
    }
}