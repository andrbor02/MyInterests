package com.example.feature_channels_ui.impl.presentation.mvi

import com.example.feature_channels_ui.impl.presentation.model.ChannelModel

internal sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        class Search(val query: String) : Ui()
        class LoadTopics(val streamId: Int) : Ui()
        object UpdateList : Ui()
        object ReloadClick : Ui()
    }

    sealed class Internal : Event() {
        class ValueLoaded(val value: List<ChannelModel>) : Internal()
        object UpdateSuccess : Internal()
        class SearchError(val error: String) : Internal()
        object TopicsLoaded : Internal()
        object TopicsError : Internal()
        class LoadingError(val error: String) : Internal()
    }
}