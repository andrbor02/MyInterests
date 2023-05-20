package com.example.feature_channels_ui.impl.presentation.stateholders

internal sealed interface OnStreamClickListener {
    fun clickOnStream(streamId: Int, streamName: String)

    interface Simple : OnStreamClickListener

    abstract class Expandable(val onTopicClickListener: OnTopicClickListener) :
        OnStreamClickListener {
        abstract fun clickOnExpandButton(streamId: Int)

        interface OnTopicClickListener {
            fun clickOnTopic(streamId: Int, topicName: String)
        }
    }
}