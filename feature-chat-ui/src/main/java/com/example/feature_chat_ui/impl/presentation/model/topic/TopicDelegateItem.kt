package com.example.feature_chat_ui.impl.presentation.model.topic

import com.example.feature_chat.impl.domain.model.TopicModel
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

class TopicDelegateItem(
    val id: Int,
    private val value: TopicModel,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as TopicDelegateItem).value == content()
    }
}
