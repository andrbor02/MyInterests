package com.example.feature_authorization.impl.domain.repository

import com.example.feature_authorization.impl.domain.model.AccountModel

internal interface AuthorizationRepository {

    suspend fun checkOrganization(organization: String): Boolean

    suspend fun fetchApiKey(email: String, password: String)

// TODO   suspend fun clearAuthorization(): Boolean
}