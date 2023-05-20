package com.example.feature_chat_ui.impl.presentation.components.reaction_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat_ui.databinding.ReactionItemBinding

class ReactionSheetAdapter(
    private val reactionUnicodeList: List<Int>,
    private val onReactionClickListener: OnReactionClickListener,
    private val reactionBottomSheetDialog: ReactionBottomSheetDialog,
    private val messageId: Int,
) :
    ListAdapter<Int, ReactionSheetAdapter.ViewHolder>(ReactionAdapterItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ReactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            reactionUnicodeList[position],
            onReactionClickListener,
            reactionBottomSheetDialog,
            messageId,
        )
    }

    class ViewHolder(
        private val binding: ReactionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            reactionUnicode: Int,
            onReactionClickListener: OnReactionClickListener,
            bottomSheetDialog: ReactionBottomSheetDialog,
            messageId: Int,
        ) {
            binding.reactionModel.text = String(Character.toChars(reactionUnicode))
            binding.reactionModel.setOnClickListener {
                onReactionClickListener.addReaction(
                    messageId,
                    ReactionModel.getEmojiName(reactionUnicode),
                )
                bottomSheetDialog.dismiss()
            }
        }
    }
}