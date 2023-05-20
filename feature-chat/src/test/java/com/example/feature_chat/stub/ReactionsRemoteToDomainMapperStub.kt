package com.example.feature_chat.stub

import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionRemote
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToDomainMapper
import com.example.feature_chat.impl.domain.model.reactions.EmojiNCU
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel

internal class ReactionsRemoteToDomainMapperStub : ReactionRemoteToDomainMapper {
    override fun invoke(reactionRemotes: List<ReactionRemote>): List<ReactionModel> {
        return listOf(ReactionModel(EmojiNCU("octopus", 0x1f419), 0))
    }
}