package com.example.feature_chat_ui.impl.presentation.model.loading

import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

class LoadingDelegateItem(
    val id: Int = 0,
    private val value: Unit = Unit,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as LoadingDelegateItem).value == content()
    }
}
