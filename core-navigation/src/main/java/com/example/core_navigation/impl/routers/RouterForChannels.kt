package com.example.core_navigation.impl.routers

interface RouterForChannels {
    fun navigateToTopic(streamId: Int, streamName: String, topicName: String)
    fun navigateToStream(streamId: Int, streamName: String)
}