package com.example.feature_authorization.impl.domain.repository

internal interface AuthorizationRepository {

    suspend fun checkOrganization(organization: String): Boolean

    suspend fun fetchApiKey(email: String, password: String)
}