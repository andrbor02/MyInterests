package com.example.feature_chat_ui.impl.presentation.model.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_ui.databinding.LoadingItemBinding
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.AdapterDelegate
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem


class LoadingDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            LoadingItemBinding.inflate(
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
        (holder as ViewHolder).bind(item.content() as Unit)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is LoadingDelegateItem

    class ViewHolder(private val binding: LoadingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Unit) {}
    }
}
