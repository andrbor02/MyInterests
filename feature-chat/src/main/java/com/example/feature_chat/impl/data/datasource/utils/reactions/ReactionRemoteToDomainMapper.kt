package com.example.feature_chat.impl.data.datasource.utils.reactions

import android.util.Log
import com.example.core_utils.SERVICE_LOG
import com.example.core_utils.myId
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionRemote
import com.example.feature_chat.impl.domain.model.reactions.EmojiNCU
import com.example.feature_chat.impl.domain.model.reactions.ReactionModel
import javax.inject.Inject

internal interface ReactionRemoteToDomainMapper {
    operator fun invoke(reactionRemotes: List<ReactionRemote>): List<ReactionModel>
}

internal class ReactionRemoteToDomainMapperImpl @Inject constructor() :
    ReactionRemoteToDomainMapper {
    override operator fun invoke(reactionRemotes: List<ReactionRemote>): List<ReactionModel> {
        val reactionModelList = mutableListOf<ReactionModel>()

        reactionRemotes.forEach { reaction ->
            val reactionCode = try {
                reaction.emojiCode.toInt(16)
            } catch (e: Exception) {
                Log.e(SERVICE_LOG, "Can't transform emoji ${reaction.emojiCode}. Exception $e")
                return@forEach
            }

            val existingReactionIndex = reactionModelList.indexOfFirst { reactionModel ->
                reactionModel.emojiNCU.code == reactionCode
            }
            val elementNotContains = (existingReactionIndex == -1)

            if (elementNotContains) {
                val isClicked = (reaction.userId == myId)
                reactionModelList.add(
                    ReactionModel(
                        emojiNCU = EmojiNCU(
                            reaction.emojiName,
                            reactionCode,
                        ),
                        count = 1,
                        isClicked = isClicked
                    )
                )
            } else {
                val existingReaction = reactionModelList[existingReactionIndex]

                if (reaction.userId == myId) {
                    reactionModelList[existingReactionIndex] = existingReaction.copy(
                        count = existingReaction.count + 1,
                        isClicked = true
                    )
                } else {
                    reactionModelList[existingReactionIndex] = existingReaction.copy(
                        count = existingReaction.count + 1
                    )
                }
            }
        }

        return reactionModelList
    }
}