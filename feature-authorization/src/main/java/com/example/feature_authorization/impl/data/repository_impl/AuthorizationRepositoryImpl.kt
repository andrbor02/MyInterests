package com.example.feature_authorization.impl.data.repository_impl


import com.example.core_network.impl.retrofit.BaseUrlHolder
import com.example.core_network.impl.retrofit.CredentialsHolder
import com.example.feature_authorization.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_authorization.impl.data.utils.RelevantOrganizationChecker
import com.example.feature_authorization.impl.domain.repository.AuthorizationRepository
import javax.inject.Inject

internal class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource,
    private val relevantOrganizationChecker: RelevantOrganizationChecker,
    private val baseUrlHolder: BaseUrlHolder,
    private val credentialsHolder: CredentialsHolder,
) : AuthorizationRepository {
    override suspend fun checkOrganization(organization: String): Boolean {
        val response = authorizationRemoteDataSource.checkOrganization(organization)
        return relevantOrganizationChecker(response)
    }

    override suspend fun fetchApiKey(email: String, password: String) {
        val apiKeyResponse = authorizationRemoteDataSource.fetchApiKey(email, password)
        credentialsHolder.setCredentials(
            userId = apiKeyResponse.userId,
            email = apiKeyResponse.email,
            apiKey = apiKeyResponse.apiKey,
        )
    }
}