package com.example.feature_chat_ui.impl.presentation.components.message_block

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.core_utils_android.extensions.paddingHorizontal
import com.example.core_utils_android.extensions.paddingVertical
import com.example.core_utils_android.extensions.sp
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import com.example.feature_chat_ui.R

class ReactionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val textBounds = Rect()

    private var emojiCode = 0x0
    private var emojiName = ""

    private var emoji = ""
    private var count = 0
    private val textToDraw: String
        get() = "$emoji $count"

    val countZeroOrLess get() = count <= 0

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 14f.sp(context)
        color = context.getColor(R.color.reaction_text_default)
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ReactionView) {
            val attrEmoji = this.getString(R.styleable.ReactionView_emoji)
            val attrCount = this.getInt(R.styleable.ReactionView_count, 0)

            emoji = attrEmoji ?: ""
            count = attrCount
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        textPaint.getTextBounds(textToDraw, 0, textToDraw.length, textBounds)

        val textWidth = textBounds.width()
        val textHeight = textBounds.height()

        val measuredWidth = resolveSize(textWidth + paddingHorizontal, widthMeasureSpec)
        val measuredHeight = resolveSize(textHeight + paddingVertical, heightMeasureSpec)

        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        val x = paddingStart.toFloat()
        val y = paddingTop.toFloat() + textBounds.height() - textPaint.descent()
        canvas.drawText(textToDraw, x, y, textPaint)
    }

    fun getEmojiCode() = emojiCode

    fun getEmojiName() = emojiName

    /**
     * @param unicodeEmoji unicode in hex (starting with 0x).
     *
     * For Example U+1F600 -> 0x1F600
     */
    fun setEmoji(unicodeEmoji: Int) {
        this.emojiCode = unicodeEmoji
        this.emoji = emojiCode.toEmoji()
        requestLayout()
    }

    fun setCount(count: Int) {
        this.count = count
        requestLayout()
    }

    fun incrementCount() {
        count++
        requestLayout()
    }

    fun decrementCount() {
        count--
        requestLayout()
    }

    fun setReaction(reactionModel: ReactionModel) {
        this.emojiCode = reactionModel.emojiNCU.code
        this.emojiName = reactionModel.emojiNCU.name
        this.emoji = emojiCode.toEmoji()
        this.count = reactionModel.count
        requestLayout()
    }

    private fun Int.toEmoji(): String {
        return if (this.isEmoji) {
            String(Character.toChars(this))
        } else {
            ""
        }
    }

    private val Int.isEmoji: Boolean
        get() = when (this) {
            0x3030, 0x00AE, 0x00A9,   // Special Characters
            in 0x1D000..0x1F77F,      // Emoticons
            in 0x2100..0x27BF,        // Misc symbols and Dingbats
            in 0x1F900..0x1F9FF,       // Supplemental Symbols and Pictographs
            -> true

            else -> false
        }
}