package com.example.`feature_authorization-read`.impl.domain.repository

import com.example.`feature_authorization-read`.impl.domain.model.AccountModel

internal interface AuthorizationRepository {

    suspend fun getAccount(): AccountModel

    suspend fun fetchApiKey(email: String, password: String): Boolean

    suspend fun getApiKey(): String

    suspend fun clearAuthorization(): Boolean
}