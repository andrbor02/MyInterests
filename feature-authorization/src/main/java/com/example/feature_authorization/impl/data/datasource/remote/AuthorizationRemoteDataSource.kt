package com.example.feature_authorization.impl.data.datasource.remote

import com.example.feature_authorization.impl.data.datasource.remote.model.api_key.ApiKeyResponse
import com.example.feature_authorization.impl.data.datasource.remote.model.organization.AuthorizationResponse

internal interface AuthorizationRemoteDataSource {

    suspend fun checkOrganization(organization: String): AuthorizationResponse

    suspend fun fetchApiKey(email: String, password: String): ApiKeyResponse
}