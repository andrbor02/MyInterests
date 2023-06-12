package com.example.feature_channels_ui.impl.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isGone
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.marginHorizontal
import com.example.core_utils_android.extensions.marginVertical
import com.example.core_utils_android.extensions.mutableMarginHorizontal
import com.example.core_utils_android.extensions.mutableMarginVertical
import com.example.core_utils_android.extensions.paddingVertical
import com.example.feature_channels_ui.R
import com.example.feature_channels_ui.databinding.ExpandableChildBinding
import com.example.feature_channels_ui.databinding.ExpandableParentBinding
import com.example.feature_channels_ui.impl.presentation.model.ChannelModel
import com.example.feature_channels_ui.impl.presentation.stateholders.OnStreamClickListener

@SuppressLint("ViewConstructor")
internal class ExpandableViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var parentView: View
    private val childrenViews = mutableListOf<View>()

    private var streamName: String = ""
    private var streamId: Int = 0
    private var isExpanded = false

    private val expandIconActual: Int
        get() = if (isExpanded) {
            R.drawable.baseline_expand_less_24
        } else {
            R.drawable.baseline_expand_more_24
        }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalWidth = MeasureSpec.getSize(widthMeasureSpec)
        var totalHeight = 0

        measureChild(parentView, widthMeasureSpec, heightMeasureSpec)
        totalHeight += parentView.measuredHeight + parentView.marginVertical

        if (isExpanded) {
            childrenViews.forEach { child ->
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                if (!child.isGone) {
                    totalHeight += child.measuredHeight + child.marginVertical
                }
            }
        }
        setMeasuredDimension(
            totalWidth,
            totalHeight + paddingVertical
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val offsetX = paddingStart
        var offsetY = paddingTop

        parentView.layout(
            offsetX + parentView.marginStart,
            offsetY + parentView.marginTop,
            offsetX + parentView.measuredWidth - parentView.marginEnd,
            offsetY + parentView.measuredHeight + parentView.marginTop
        )

        offsetY += parentView.measuredHeight + parentView.marginVertical
        if (isExpanded) {
            childrenViews.forEach { child ->
                child.layout(
                    offsetX + child.marginStart,
                    offsetY + child.marginTop,
                    offsetX + child.measuredWidth + child.marginStart,
                    offsetY + child.measuredHeight + child.marginTop
                )
                offsetY += child.measuredHeight + child.marginHorizontal
            }

        }
    }

    fun setParent(
        channelModel: ChannelModel,
        onStreamClickListener: OnStreamClickListener,
    ) {
        removeAllViews()
        childrenViews.clear()

        streamName = channelModel.name
        streamId = channelModel.id
        isExpanded = channelModel.isExpanded

        val binding = ExpandableParentBinding.inflate(LayoutInflater.from(context), this, false)
        with(binding) {
            parentView = root

            stream.text = buildString {
                this.append("#")
                this.append(channelModel.name)
            }
            root.layoutParams = MarginLayoutParams(generateDefaultLayoutParams()).apply {
                mutableMarginHorizontal = PARENT_MARGIN_HORIZONTAL
                mutableMarginVertical = PARENT_MARGIN_VERTICAL
            }

            when (onStreamClickListener) {
                is OnStreamClickListener.Simple -> {
                    expandIcon.hide()
                }

                is OnStreamClickListener.Expandable -> {
                    expandIcon.setImageResource(expandIconActual)
                    expandIcon.setOnClickListener {
                        onStreamClickListener.clickOnExpandButton(channelModel.id)
                        isExpanded = !isExpanded
                        expandIcon.setImageResource(expandIconActual)
                        requestLayout()
                    }
                }
            }
            expandableParent.setOnClickListener {
                onStreamClickListener.clickOnStream(channelModel.id, channelModel.name)
            }
        }
        addView(parentView)
    }

    fun setChildren(
        namesWithCounts: List<Pair<String, Int>>,
        onTopicClickListener: OnStreamClickListener.Expandable.OnTopicClickListener,
    ) {
        if (namesWithCounts.isEmpty()) {
            return
        }

        namesWithCounts.forEachIndexed { index, (name, count) ->
            val binding = ExpandableChildBinding.inflate(LayoutInflater.from(context), this, false)

            with(binding) {
                topic.text = name
                root.layoutParams = MarginLayoutParams(generateDefaultLayoutParams())
                root.background = AppCompatResources.getDrawable(context, colorsList[index % 2])
                root.setOnClickListener {
                    onTopicClickListener.clickOnTopic(
                        streamId = streamId,
                        topicName = name
                    )
                }

                childrenViews.add(root)
                addView(root)
            }
        }
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
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

    private companion object {
        private val colorsList = listOf(
            R.color.first_topic_color,
            R.color.second_topic_color,
        )

        private const val PARENT_MARGIN_VERTICAL = 70
        private const val PARENT_MARGIN_HORIZONTAL = 30
    }
}