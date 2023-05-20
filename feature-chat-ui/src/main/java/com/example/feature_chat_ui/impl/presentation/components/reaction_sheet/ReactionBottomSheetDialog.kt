package com.example.feature_chat_ui.impl.presentation.components.reaction_sheet

import android.content.Context
import android.os.Bundle
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat_ui.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReactionBottomSheetDialog(
    context: Context,
    private val onReactionClickListener: OnReactionClickListener,
    private val messageId: Int,
) : BottomSheetDialog(context) {

    private val binding: BottomSheetBinding by lazy {
        BottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ReactionSheetAdapter(
            ReactionModel.emojiList,
            onReactionClickListener,
            this,
            messageId,
        )
        binding.reactionRecycler.adapter = adapter
        adapter.submitList(ReactionModel.emojiList)
    }
}