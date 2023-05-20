package com.example.feature_chat_ui.impl.presentation.components.reaction_sheet

interface OnReactionClickListener {
    fun addReaction(messageId: Int, emojiName: String)

    fun removeReaction(messageId: Int, emojiName: String)
}