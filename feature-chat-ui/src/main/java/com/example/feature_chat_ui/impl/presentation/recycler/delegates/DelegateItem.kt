package com.example.feature_chat_ui.impl.presentation.recycler.delegates

interface DelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: DelegateItem): Boolean
}
