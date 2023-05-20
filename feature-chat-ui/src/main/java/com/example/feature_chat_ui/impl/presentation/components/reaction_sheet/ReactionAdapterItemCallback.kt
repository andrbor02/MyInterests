package com.example.feature_chat_ui.impl.presentation.components.reaction_sheet

import androidx.recyclerview.widget.DiffUtil

class ReactionAdapterItemCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}