package com.example.feature_chat_ui.impl.presentation.components.message_block

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.children
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.example.core_utils_android.extensions.marginHorizontal
import com.example.core_utils_android.extensions.marginVertical
import com.example.core_utils_android.extensions.mutableMarginHorizontal
import com.example.core_utils_android.extensions.mutableMarginVertical
import com.example.core_utils_android.extensions.mutablePaddingBottom
import com.example.core_utils_android.extensions.mutablePaddingHorizontal
import com.example.core_utils_android.extensions.mutablePaddingTop
import com.example.core_utils_android.extensions.mutablePaddingVertical
import com.example.core_utils_android.extensions.paddingHorizontal
import com.example.core_utils_android.extensions.paddingVertical
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat_ui.R
import com.example.feature_chat_ui.impl.presentation.model.message.ReactionClickListener

@SuppressLint("ViewConstructor")
class FlexBoxViewGroup @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0,
    private val onAddClickListener: OnClickListener,
    private val reactionClickListener: ReactionClickListener,
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private val elements = mutableListOf<View>()
    private val elementsInLines = mutableListOf<Int>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        rememberChildViews()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val maxWidth = MeasureSpec.getSize(widthMeasureSpec)

        var elementsInLineIndex = 0

        var widthUsed = 0
        var heightUsed = 0

        var totalWidth = 0
        var totalHeight = 0

        elementsInLines.clear()
        elementsInLines.add(0, 0)

        for ((elementsCount, element) in elements.withIndex()) {
            measureChild(
                element,
                widthMeasureSpec,
                heightMeasureSpec,
            )

            val shouldFillNextLine =
                widthUsed + element.measuredWidth + element.marginHorizontal >= maxWidth
            if (shouldFillNextLine) {
                widthUsed = 0
                totalHeight += heightUsed

                elementsInLineIndex++
                elementsInLines.add(elementsInLineIndex, 0)
            }

            widthUsed += element.measuredWidth + element.marginHorizontal
            heightUsed = maxOf(heightUsed, element.measuredHeight + element.marginVertical)

            totalWidth = maxOf(totalWidth, widthUsed)
            totalHeight = maxOf(totalHeight, heightUsed)

            elementsInLines[elementsInLineIndex] = elementsCount + 1
        }

        setMeasuredDimension(
            totalWidth + paddingHorizontal,
            totalHeight + paddingVertical
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var offsetX = paddingStart
        var offsetY = paddingTop

        var maxLineHeight = 0
        var stringNumber = 0

        for ((index, element) in elements.withIndex()) {
            if (elementsInLines.size > 1 && elementsInLines[stringNumber] == index) {
                offsetY += maxLineHeight
                offsetX = paddingStart
                stringNumber++
            }

            element.layout(
                offsetX + element.marginStart,
                offsetY + element.marginTop,
                offsetX + element.measuredWidth + element.marginStart,
                offsetY + element.measuredHeight + element.marginTop
            )

            offsetX += element.measuredWidth + element.marginHorizontal
            maxLineHeight = element.measuredHeight + element.marginVertical
        }
    }

    fun addReactionList(reactionModelList: List<ReactionModel>) {
        for (reaction in reactionModelList) {
            val reactionView = createDefaultReactionView(reaction)
            addReactionView(reactionView)
        }
        rememberChildViews()
    }

    fun addReaction(reactionModel: ReactionModel) {
        val reactionView = createDefaultReactionView(reactionModel)
        addReactionView(reactionView)
        rememberChildViews()
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

    private fun rememberChildViews() {
        var currentChild: View
        for (i in 0 until childCount) {
            currentChild = getChildAt(i)
            val beforeLastIndex = if (elements.size == 0) {
                0
            } else {
                elements.size - 1
            }

            if (elements.contains(currentChild)) {
                continue
            }
            when (currentChild) {
                is ReactionView -> {
                    elements.add(beforeLastIndex, currentChild)
                    currentChild.setOnClickListener(reactionListener)
                }

                is ImageView -> {
                    elements.add(currentChild)
                }
            }
        }
        shouldShowAddButton()
    }

    private fun createDefaultReactionView(reactionModel: ReactionModel) =
        ReactionView(context).apply {
            setReaction(reactionModel)
            mutablePaddingHorizontal = 27
            mutablePaddingTop = 21
            mutablePaddingBottom = 30
            layoutParams = MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
            ).apply {
                mutableMarginHorizontal = 27
                mutableMarginVertical = 15
            }

            background = AppCompatResources.getDrawable(
                context,
                R.drawable.reaction_view_background
            )
            if (reactionModel.isClicked) {
                isSelected = true
            }
        }

    private fun shouldShowAddButton() {
        val elementsContainReactions = elements.size >= 1 && elements.first() !is ImageView
        if (elementsContainReactions) {
            showAddButton()
        } else {
            hideAddButton()
        }
    }

    private fun showAddButton() {
        if (elements.last() is ImageView) {
            return
        }

        val add = ImageView(context, attrs, defStyleAttr, defStyleRes).apply {
            setImageResource(R.drawable.baseline_add_24)
            background =
                AppCompatResources.getDrawable(context, R.drawable.reaction_view_background_default)
            mutablePaddingHorizontal = 48
            mutablePaddingVertical = 12
            layoutParams = MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
            ).apply {
                mutableMarginHorizontal = 18
                mutableMarginVertical = 15
            }
            setOnClickListener(onAddClickListener)
        }

        addView(add)
        rememberChildViews()
    }

    private fun hideAddButton() {
        val add = children.find { it is ImageView }
        if (add != null) {
            removeElement(add)
        }
    }

    private fun addReactionView(newReactionView: ReactionView) {
        val beforeLastIndex = elements.size - 1
        addView(newReactionView, beforeLastIndex)
    }

    private val reactionListener = OnClickListener { view ->
        with(view) {
            if (this is ReactionView) {
                if (isSelected) {
                    decrementCount()
                    reactionClickListener.remove(this.getEmojiName())
                } else {
                    incrementCount()
                    reactionClickListener.add(this.getEmojiName())
                }
                isSelected = !isSelected

                checkRemoveReaction(this)
            }
        }
    }

    private fun checkRemoveReaction(reaction: ReactionView) {
        if (reaction.countZeroOrLess) {
            removeElement(reaction)
        }
    }

    private fun removeElement(child: View) {
        removeView(child)
        elements.remove(child)
        rememberChildViews()
    }
}