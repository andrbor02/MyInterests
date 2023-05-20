package com.example.fintechrecyclerview.delegates.expense

import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

class MessageDelegateItem(
    val id: Int,
    private val value: MessageModel,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as MessageDelegateItem).value == content()
    }
}
