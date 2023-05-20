package com.example.feature_chat.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessageResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.MessagesResponse
import com.example.feature_chat.impl.data.datasource.remote.model.messages.SendMessageResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MessagesApi {

    @GET("messages")
    suspend fun getMessagesByAnchor(
        @Query("num_before") beforeAnchor: Int,
        @Query("num_after") afterAnchor: Int,
        @Query("anchor") anchorValue: String,
        @Query("narrow") narrow: String,
    ): GenericResponse<MessagesResponse>

    @GET("messages")
    suspend fun getMessagesByMessageId(
        @Query("num_before") beforeAnchor: Int,
        @Query("num_after") afterAnchor: Int,
        @Query("anchor") messageId: Int,
        @Query("narrow") narrow: String,
    ): GenericResponse<MessagesResponse>

    @GET("messages/{message_id}")
    suspend fun getSingleMessage(@Path("message_id") messageId: Int): GenericResponse<MessageResponse>

    @POST("messages?type=stream")
    suspend fun sendMessage(
        @Query("to") stream: String,
        @Query("topic") topic: String,
        @Query("content") text: String,
    ): GenericResponse<SendMessageResponse>
}