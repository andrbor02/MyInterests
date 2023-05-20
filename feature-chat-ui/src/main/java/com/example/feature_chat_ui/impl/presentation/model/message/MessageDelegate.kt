package com.example.feature_chat_ui.impl.presentation.model.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat_ui.databinding.MessageItemBinding
import com.example.feature_chat_ui.impl.presentation.components.reaction_sheet.OnReactionClickListener
import com.example.feature_chat_ui.impl.presentation.components.reaction_sheet.ReactionBottomSheetDialog
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.AdapterDelegate
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem
import com.example.fintechrecyclerview.delegates.expense.MessageDelegateItem

class MessageDelegate(
    private val context: Context,
    private val onReactionClickListener: OnReactionClickListener,
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            MessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context,
            onReactionClickListener
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as ViewHolder).bind(
            item.content() as MessageModel
        )
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is MessageDelegateItem

    class ViewHolder(
        private val binding: MessageItemBinding,
        private val context: Context,
        private val onReactionClickListener: OnReactionClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val onClickLambda: (
            context: Context,
            onReactionClickListener: OnReactionClickListener,
            messageId: Int,
        ) -> Unit = { context, onReactionClickListener, messageId ->
            val bottomSheetDialog = ReactionBottomSheetDialog(
                context,
                onReactionClickListener,
                messageId,
            )
            bottomSheetDialog.show()
        }

        fun bind(message: MessageModel) {
            with(binding) {

                this.message.setOnLongClickListener {
                    onClickLambda(context, onReactionClickListener, message.id)
                    true
                }

                this.message.setOnAddClickListener {
                    onClickLambda(
                        context,
                        onReactionClickListener,
                        message.id
                    )
                }

                this.message.setOnReactionClickListener(
                    object : ReactionClickListener {
                        override fun add(reactionName: String) {
                            onReactionClickListener.addReaction(message.id, reactionName)
                        }

                        override fun remove(reactionName: String) {
                            onReactionClickListener.removeReaction(message.id, reactionName)
                        }
                    }
                )

                this.message.setMessage(message)
            }
        }
    }
}
