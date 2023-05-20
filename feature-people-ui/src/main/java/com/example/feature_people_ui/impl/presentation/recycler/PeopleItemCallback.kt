package com.example.feature_people_ui.impl.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_people.impl.domain.model.UserModel

internal class PeopleItemCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}