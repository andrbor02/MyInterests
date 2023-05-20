package com.example.fintechrecyclerview.delegates.date

import com.example.feature_chat.impl.domain.model.DateModel
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

class DateDelegateItem(
    val id: Int,
    private val value: DateModel,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as DateDelegateItem).value == content()
    }
}
