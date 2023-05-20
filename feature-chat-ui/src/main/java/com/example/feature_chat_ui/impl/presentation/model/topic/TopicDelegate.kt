package com.example.feature_chat_ui.impl.presentation.model.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat.impl.domain.model.TopicModel
import com.example.feature_chat_ui.databinding.TopicItemBinding
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.AdapterDelegate
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem

class TopicDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as ViewHolder).bind(item.content() as TopicModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TopicDelegateItem

    class ViewHolder(private val binding: TopicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TopicModel) {
            with(binding) {
                topic.text = model.name
            }
        }
    }
}
