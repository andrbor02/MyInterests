package com.example.feature_profile_ui.impl.presentation.mvi

internal sealed class Command {
    class LoadValue(val id: Int) : Command()
    object Logout : Command()
}