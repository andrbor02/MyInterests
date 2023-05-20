package com.example.feature_chat.impl.domain.model.reactions

data class ReactionModel(
    val emojiNCU: EmojiNCU,
    val count: Int = 0,
    val isClicked: Boolean = false,
) {
    companion object {
        fun getEmojiName(emojiCode: Int): String {
            val emoji = EmojiNCU.emojiSetNCU.first { emojiNCU ->
                emojiNCU.code == emojiCode
            }
            return emoji.name
        }

        fun getEmojiCode(emojiName: String): Int {
            val emoji = EmojiNCU.emojiSetNCU.first { emojiNCU ->
                emojiNCU.name == emojiName
            }
            return emoji.code
        }

        val emojiList = listOf(
            0x1F602,
            0x2764,
            0x1F60D,
            0x1F923,
            0x1F60A,
            0x1F64F,
            0x1F495,
            0x1F62D,
            0x1F525,
            0x1F618,
            0x1F44D,
            0x1F970,
            0x1F60E,
            0x1F606,
            0x1F601,
            0x1F609,
            0x1F914,
            0x1F605,
            0x1F614,
            0x1F644,
            0x1F61C,
            0x1F612,
            0x1F629,
            0x263A,
            0x1F601,
            0x1F44C,
            0x1F44F,
            0x1F494,
            0x1F496,
            0x1F499,
            0x1F622,
            0x1F4AA,
            0x1F60B,
            0x1F917,
            0x1F49C,
            0x1F60E,
            0x1F607,
            0x1F339,
            0x1F926,
            0x1F389,
            0x1F49E,
            0x270C,
            0x2728,
            0x1F937,
            0x1F631,
            0x1F60C,
            0x1F338,
            0x1F64C,
        )
    }
}