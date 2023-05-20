package com.example.feature_chat.impl.domain.model.utils

data class LoadSettings(
    val anchor: Anchor,
    val beforeAnchor: Int = 0,
    val afterAnchor: Int = 0,
) {

    companion object {
        fun loadUp(messageId: Int = -1) = LoadSettings(
            anchor = Anchor.MessageId(messageId),
            beforeAnchor = DEFAULT_PORTION,
        )

        fun loadDown(messageId: Int) = LoadSettings(
            anchor = Anchor.MessageId(messageId),
            afterAnchor = DEFAULT_PORTION,
        )

        fun loadInitial() = LoadSettings(
            anchor = Anchor.Special(DEFAULT_ANCHOR),
            beforeAnchor = DEFAULT_PORTION
        )

        fun loadSingle(messageId: Int) = LoadSettings(
            anchor = Anchor.MessageId(messageId),
        )

        private const val DEFAULT_ANCHOR = "newest"
        private const val DEFAULT_PORTION = 21
    }
}