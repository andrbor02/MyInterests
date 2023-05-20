package com.example.feature_streams.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.all.StreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.created.CreatedStreamResponse
import com.example.feature_streams.impl.data.datasource.remote.model.streams.subscribed.SubscribedStreamsResponse
import com.example.feature_streams.impl.data.datasource.remote.model.topics.TopicsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface StreamApi {

    @GET("streams")
    suspend fun getStreams():
            GenericResponse<StreamsResponse>

    @GET("users/me/subscriptions")
    suspend fun getSubscribedStreams():
            GenericResponse<SubscribedStreamsResponse>

    @GET("users/me/{streamId}/topics")
    suspend fun getTopics(@Path("streamId") id: Int):
            GenericResponse<TopicsResponse>

    @FormUrlEncoded
    @POST("users/me/subscriptions")
    suspend fun createStream(
        @Field("subscriptions") subscriptions: String,
        @Field("history_public_to_subscribers") isPublic: Boolean,
    ): GenericResponse<CreatedStreamResponse>
}