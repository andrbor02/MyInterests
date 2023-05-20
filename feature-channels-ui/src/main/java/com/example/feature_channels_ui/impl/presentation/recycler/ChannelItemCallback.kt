package com.example.feature_channels_ui.impl.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_channels_ui.impl.presentation.model.ChannelModel

internal class ChannelItemCallback : DiffUtil.ItemCallback<ChannelModel>() {
    override fun areItemsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return oldItem == newItem
    }
}