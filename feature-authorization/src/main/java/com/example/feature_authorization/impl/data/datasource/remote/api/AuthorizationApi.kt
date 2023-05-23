package com.example.feature_authorization.impl.data.datasource.remote.api

import com.example.core_network.impl.retrofit.call_adapter.GenericResponse
import com.example.feature_authorization.impl.data.datasource.remote.model.api_key.ApiKeyResponse
import com.example.feature_authorization.impl.data.datasource.remote.model.organization.AuthorizationResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AuthorizationApi {

    @GET("server_settings")
    suspend fun getOrganizationSettings(): GenericResponse<AuthorizationResponse>

    @POST("fetch_api_key")
    suspend fun fetchApiKey(
        @Query("username") email: String,
        @Query("password") password: String,
    ): GenericResponse<ApiKeyResponse>
}