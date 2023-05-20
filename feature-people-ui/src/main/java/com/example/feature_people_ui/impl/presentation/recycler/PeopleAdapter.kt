package com.example.feature_people_ui.impl.presentation.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core_ui.R
import com.example.feature_people.impl.domain.model.StatusModel
import com.example.feature_people.impl.domain.model.UserModel
import com.example.feature_people_ui.databinding.UserItemBinding

internal class PeopleAdapter(
    private val onUserClickListener: OnUserClickListener,
) : ListAdapter<UserModel, PeopleAdapter.PeopleViewHolder>(PeopleItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val itemUserBinding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PeopleViewHolder(itemUserBinding, parent.context, onUserClickListener)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PeopleViewHolder(
        private val binding: UserItemBinding,
        private val context: Context,
        private val onUserClickListener: OnUserClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            with(binding) {
                Glide
                    .with(this.root)
                    .load(user.avatar)
                    .centerCrop()
                    .placeholder(R.drawable.no_photography_48)
                    .into(binding.avatar)

                val color = when (user.status) {
                    StatusModel.ACTIVE -> R.color.online_color
                    StatusModel.IDLE -> R.color.idle_color
                    StatusModel.OFFLINE -> R.color.offline_color
                }
                status.setBackgroundColor(context.getColor(color))

                name.text = user.name

                email.text = user.email

                root.setOnClickListener {
                    onUserClickListener.click(user.id)
                }
            }
        }
    }
}