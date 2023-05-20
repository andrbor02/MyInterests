package com.example.feature_authorization_write.impl.domain.repository

import com.example.feature_authorization_write.impl.domain.model.AccountModel

internal interface AuthorizationWriteRepository {

    suspend fun getAccount(): AccountModel

    suspend fun fetchApiKey(email: String, password: String): Boolean

    suspend fun getApiKey(): String

    suspend fun clearAuthorization(): Boolean
}