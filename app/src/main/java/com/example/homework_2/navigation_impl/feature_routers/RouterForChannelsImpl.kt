package com.example.homework_2.navigation_impl.feature_routers

import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.homework_2.navigation_impl.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RouterForChannelsImpl @Inject constructor(
    private val router: Router,
) : RouterForChannels {
    override fun navigateToTopic(streamId: Int, streamName: String, topicName: String) {
        router.navigateTo(Screens.Chat(streamId, streamName, topicName))
    }

    override fun navigateToStream(streamId: Int, streamName: String) {
        router.navigateTo(Screens.Chat(streamId, streamName))
    }
}

