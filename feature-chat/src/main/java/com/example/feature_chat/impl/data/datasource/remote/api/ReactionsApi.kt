package com.example.feature_chat.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionsResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ReactionsApi {

    @FormUrlEncoded
    @POST("messages/{message_id}/reactions")
    suspend fun addReaction(
        @Path("message_id") messageId: Int,
        @Field("emoji_name") emojiName: String,
    ): GenericResponse<ReactionsResponse>

    @DELETE("messages/{message_id}/reactions")
    suspend fun removeReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
    ): GenericResponse<ReactionsResponse>
}