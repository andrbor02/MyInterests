package com.example.feature_chat.impl.data.datasource.utils.reactions

import com.example.feature_chat.impl.data.datasource.local.model.ReactionLocal
import com.example.feature_chat.impl.domain.model.reactions.EmojiNCU
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import javax.inject.Inject

internal interface ReactionLocalToDomainMapper {
    operator fun invoke(reactionLocal: ReactionLocal): ReactionModel
}

internal class ReactionLocalToDomainMapperImpl @Inject constructor() :
    ReactionLocalToDomainMapper {
    override operator fun invoke(reactionLocal: ReactionLocal): ReactionModel {
        return ReactionModel(
            emojiNCU = EmojiNCU(
                name = reactionLocal.name,
                code = reactionLocal.code
            ),
            count = reactionLocal.count,
            isClicked = reactionLocal.isClicked,
        )
    }
}