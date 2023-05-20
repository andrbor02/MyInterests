package com.example.feature_channels_ui.impl.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_channels_ui.databinding.ChannelItemBinding
import com.example.feature_channels_ui.impl.presentation.model.ChannelModel
import com.example.feature_channels_ui.impl.presentation.stateholders.OnStreamClickListener

internal class ChannelAdapter(
    private val onStreamClickListener: OnStreamClickListener,
) :
    ListAdapter<ChannelModel, ChannelAdapter.ChannelViewHolder>(ChannelItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {

        val itemUserBinding =
            ChannelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ChannelViewHolder(
            itemUserBinding,
            onStreamClickListener,
        )
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChannelViewHolder(
        private val binding: ChannelItemBinding,
        private val onStreamClickListener: OnStreamClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(channel: ChannelModel) {
            when (onStreamClickListener) {
                is OnStreamClickListener.Simple -> {
                    binding.channel.setParent(
                        channel,
                        onStreamClickListener,
                    )
                }

                is OnStreamClickListener.Expandable -> {
                    binding.channel.setParent(
                        channel,
                        onStreamClickListener,
                    )
                    val pairs = mutableListOf<Pair<String, Int>>()
                    channel.topics.forEach { topic ->
                        pairs.add(topic.name to topic.messagesCount)
                    }
                    binding.channel.setChildren(
                        pairs,
                        onStreamClickListener.onTopicClickListener
                    )
                }
            }
        }
    }
}