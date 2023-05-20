package com.example.feature_authorization_write.impl.data.repository_impl


import com.example.feature_authorization_write.impl.domain.model.AccountModel
import com.example.feature_authorization_write.impl.domain.repository.AuthorizationWriteRepository
import com.example.feature_authorization_write.impl.data.datasource.local.AuthorizationWriteLocalDataSource
import com.example.feature_authorization_write.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_authorization_write.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_authorization_write.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import javax.inject.Inject

internal class AuthorizationWriteRepositoryImpl @Inject constructor(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource,
    private val authorizationWriteLocalDataSource: AuthorizationWriteLocalDataSource,
    private val usersWithPresenceNetworkToLocalMapper: UserWithPresenceNetworkToLocalMapper,
    private val userLocalToDomainMapper: UserLocalToDomainMapper,
) : AuthorizationWriteRepository {
    override suspend fun getAccount(): AccountModel {
        TODO("Not yet implemented")
    }

    override suspend fun fetchApiKey(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getApiKey(): String {
        TODO("Not yet implemented")
    }

    override suspend fun clearAuthorization() {
        TODO("Not yet implemented")
    }


}