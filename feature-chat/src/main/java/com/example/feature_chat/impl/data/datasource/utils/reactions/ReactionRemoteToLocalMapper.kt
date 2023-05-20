package com.example.feature_chat.impl.data.datasource.utils.reactions

import android.util.Log
import com.example.core_utils.SERVICE_LOG
import com.example.core_utils.myId
import com.example.feature_chat.impl.data.datasource.local.model.ReactionLocal
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionRemote
import javax.inject.Inject

internal interface ReactionRemoteToLocalMapper {
    operator fun invoke(reactionRemotes: List<ReactionRemote>): List<ReactionLocal>
}

internal class ReactionRemoteToLocalMapperImpl @Inject constructor() :
    ReactionRemoteToLocalMapper {
    override operator fun invoke(reactionRemotes: List<ReactionRemote>): List<ReactionLocal> {
        val reactionModelList = mutableListOf<ReactionLocal>()

        reactionRemotes.forEach { reaction ->
            val reactionCode = try {
                reaction.emojiCode.toInt(16)
            } catch (e: Exception) {
                Log.e(SERVICE_LOG, "Can't transform emoji ${reaction.emojiCode}. Exception $e")
                return@forEach
            }

            val existingReactionIndex = reactionModelList.indexOfFirst { reactionModel ->
                reactionModel.code == reactionCode
            }
            val elementNotContains = (existingReactionIndex == -1)

            if (elementNotContains) {
                val isClicked = (reaction.userId == myId)
                reactionModelList.add(
                    ReactionLocal(
                        name = reaction.emojiName,
                        code = reactionCode,
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