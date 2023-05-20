package com.example.feature_channels_ui.impl.presentation.mvi

internal sealed class Command {
    object LoadList : Command()
    class SearchValue(val query: String) : Command()
    class LoadTopics(val streamId: Int) : Command()
    object UpdateList : Command()
    class RegisterExpand(val streamId: Int) : Command()
}