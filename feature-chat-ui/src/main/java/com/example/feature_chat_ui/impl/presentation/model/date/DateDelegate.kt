package com.example.feature_chat_ui.impl.presentation.model.date

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat.impl.domain.model.DateModel
import com.example.feature_chat_ui.databinding.DateItemBinding
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.AdapterDelegate
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem
import com.example.fintechrecyclerview.delegates.date.DateDelegateItem


class DateDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            DateItemBinding.inflate(
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
        (holder as ViewHolder).bind(item.content() as DateModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is DateDelegateItem

    class ViewHolder(private val binding: DateItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DateModel) {
            with(binding) {
                date.text = model.date
            }
        }
    }
}
