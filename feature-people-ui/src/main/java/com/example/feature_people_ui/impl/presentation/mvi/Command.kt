package com.example.feature_people_ui.impl.presentation.mvi

internal sealed class Command {
    object LoadList : Command()
    class Search(val query: String) : Command()
    object UpdateList : Command()
}