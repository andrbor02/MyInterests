package com.example.feature_authorization_read.impl.data.repository_impl


import com.example.feature_authorization_read.impl.domain.model.AccountModel
import com.example.`feature_authorization-read`.impl.domain.repository.AuthorizationRepository
import com.example.feature_people.impl.data.datasource.local.UsersLocalDataSource
import com.example.feature_people.impl.data.datasource.remote.UsersRemoteDataSource
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import javax.inject.Inject

internal class AuthorizationRepositoryImpl @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource,
    private val usersWithPresenceNetworkToLocalMapper: UserWithPresenceNetworkToLocalMapper,
    private val userLocalToDomainMapper: UserLocalToDomainMapper,
) : AuthorizationRepository {
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