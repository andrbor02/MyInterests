package com.example.feature_chat_ui.impl.presentation.model.message

interface ReactionClickListener {
    fun add(reactionName: String)

    fun remove(reactionName: String)
}