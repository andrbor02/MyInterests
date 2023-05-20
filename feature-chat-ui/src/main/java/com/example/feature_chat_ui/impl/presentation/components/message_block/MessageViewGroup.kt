package com.example.feature_chat_ui.impl.presentation.components.message_block

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import com.example.core_utils.SERVICE_LOG
import com.example.core_utils_android.extensions.marginHorizontal
import com.example.core_utils_android.extensions.marginVertical
import com.example.core_utils_android.extensions.mutableMarginBottom
import com.example.core_utils_android.extensions.mutableMarginEnd
import com.example.core_utils_android.extensions.mutableMarginHorizontal
import com.example.core_utils_android.extensions.mutableMarginStart
import com.example.core_utils_android.extensions.mutableMarginVertical
import com.example.core_utils_android.extensions.paddingHorizontal
import com.example.core_utils_android.extensions.paddingVertical
import com.example.core_utils_android.extensions.sp
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat_ui.R
import com.example.feature_chat_ui.impl.presentation.model.message.ReactionClickListener
import com.google.android.material.imageview.ShapeableImageView

@SuppressLint("ViewConstructor")
class MessageViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var avatar: View
    private lateinit var name: View
    private lateinit var time: View
    private lateinit var text: View
    private lateinit var reactions: FlexBoxViewGroup

    private var onAddClickListener: View.OnClickListener? = null
    private var reactionClickListener: ReactionClickListener? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val (avatarWidth, avatarHeight) = getDimensionsWithMargins(
            avatar,
            widthMeasureSpec,
            heightMeasureSpec
        )

        val (timeWidth, timeHeight) = getDimensionsWithMargins(
            time,
            widthMeasureSpec,
            heightMeasureSpec
        )

        val maxMidSectionWidth = MeasureSpec.getSize(widthMeasureSpec) - avatarWidth - timeWidth
        val midSectionMeasureSpec =
            MeasureSpec.makeMeasureSpec(maxMidSectionWidth, MeasureSpec.AT_MOST)
        val (nameWidth, nameHeight) = getDimensionsWithMargins(
            name,
            midSectionMeasureSpec,
            heightMeasureSpec
        )

        val (textWidth, textHeight) = getDimensionsWithMargins(
            text,
            midSectionMeasureSpec,
            heightMeasureSpec
        )

        val (reactionsWidth, reactionsHeight) = getDimensionsWithMargins(
            reactions,
            midSectionMeasureSpec,
            heightMeasureSpec
        )

        val maxWidth = MeasureSpec.getSize(widthMeasureSpec)

        val midSectionWidth = maxOf(nameWidth, textWidth, reactionsWidth)
        val widthSum = avatarWidth + midSectionWidth + timeWidth
        val totalWidth = minOf(maxWidth, widthSum)

        val midSectionHeight = maxOf(avatarHeight, nameHeight + textHeight) + reactionsHeight
        val totalHeight = maxOf(avatarHeight, midSectionHeight, timeHeight)

        setMeasuredDimension(
            totalWidth + paddingHorizontal,
            totalHeight + paddingVertical
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var offsetX = paddingStart
        var offsetY = paddingTop

        avatar.layout(
            offsetX + avatar.marginStart,
            offsetY + avatar.marginTop,
            offsetX + avatar.measuredWidth + avatar.marginStart,
            offsetY + avatar.measuredHeight + avatar.marginTop
        )

        offsetX += avatar.measuredWidth + avatar.marginHorizontal
        val endBaseline = measuredWidth - time.measuredWidth - time.marginHorizontal

        name.layout(
            offsetX + name.marginStart,
            offsetY + name.marginTop,
            endBaseline - name.marginEnd,
            offsetY + name.measuredHeight + name.marginTop
        )

        offsetY += name.measuredHeight + name.marginVertical

        text.layout(
            offsetX + text.marginStart,
            offsetY + text.marginTop,
            endBaseline - text.marginEnd,
            offsetY + text.measuredHeight + text.marginTop
        )

        offsetY += text.measuredHeight + text.marginVertical

        reactions.layout(
            offsetX + reactions.marginStart,
            measuredHeight - reactions.measuredHeight - reactions.marginBottom,
            endBaseline - reactions.marginEnd,
            measuredHeight - reactions.marginBottom
        )

        time.layout(
            measuredWidth - time.marginEnd - time.measuredWidth,
            paddingTop + time.marginTop,
            measuredWidth - time.marginEnd,
            paddingTop + time.measuredHeight + time.marginTop
        )
    }

    fun setOnAddClickListener(onClickListener: OnClickListener) {
        onAddClickListener = onClickListener
    }

    fun setOnReactionClickListener(reactionClickListener: ReactionClickListener) {
        this.reactionClickListener = reactionClickListener
    }

    fun addReaction(reaction: ReactionModel) {
        reactions.addReaction(reaction)
    }

    fun setMessage(message: MessageModel) {
        removeAllViews()
        this.background = AppCompatResources.getDrawable(
            context,
            R.drawable.message_background
        )
        this.layoutParams =
            MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                mutableMarginVertical = 15
            }

        this.avatar =
            ShapeableImageView(context).apply {
                layoutParams =
                    MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                        mutableMarginHorizontal = 60
                        mutableMarginVertical = 28
                    }
                Glide
                    .with(this)
                    .load(message.avatar)
                    .circleCrop()
                    .placeholder(com.example.core_ui.R.drawable.no_photography_48)
                    .into(this)
            }
        addView(avatar)

        this.name = TextView(context).apply {
            layoutParams =
                MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    mutableMarginBottom = 12
                }
            text = message.name
            textSize = 4f.sp(context)
            setTextColor(resources.getColor(R.color.sender_name_color))
        }
        addView(name)

        this.time = TextView(context).apply {
            layoutParams =
                MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    mutableMarginStart = 28
                    mutableMarginEnd = 50
                }
            text = message.time
        }
        addView(time)

        this.text = TextView(context).apply {
            layoutParams =
                MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    mutableMarginVertical = 12
                }
            text = message.text
        }
        addView(text)

        this.reactions = FlexBoxViewGroup(
            context,
            onAddClickListener = onAddClickListener ?: EmptyAddListener,
            reactionClickListener = reactionClickListener ?: EmptyReactionListener,
        ).apply {
            layoutParams = generateDefaultLayoutParams()
            val reactionList = mutableListOf<ReactionModel>()
            for (reaction in message.reactions) {
                reactionList.add(reaction)
            }
            addReactionList(reactionList)
        }

        addView(reactions)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun checkLayoutParams(p: LayoutParams): Boolean {
        return p is MarginLayoutParams
    }

    private object EmptyAddListener : OnClickListener {
        override fun onClick(v: View?) {
            Log.e(SERVICE_LOG, "AddClickListener not found")
        }
    }

    private object EmptyReactionListener : ReactionClickListener {
        override fun add(reactionName: String) {
            Log.e(SERVICE_LOG, "ReactionClickListener not found")
        }

        override fun remove(reactionName: String) {
            Log.e(SERVICE_LOG, "ReactionClickListener not found")
        }
    }

    private fun getDimensionsWithMargins(
        view: View,
        widthMeasureSpec: Int,
        heightMeasureSpec: Int,
    ): Pair<Int, Int> {
        measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, 0)
        val width = view.measuredWidth + view.marginHorizontal
        val height = view.measuredHeight + view.marginVertical
        return width to height
    }
}