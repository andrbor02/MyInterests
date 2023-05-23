package com.example.feature_authorization.impl.data.datasource.remote.impl

import android.util.Log
import com.example.core_network.impl.retrofit.BaseUrlHolder
import com.example.core_utils.common_helpers.isNull
import com.example.feature_authorization.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_authorization.impl.data.datasource.remote.api.AuthorizationApi
import com.example.feature_authorization.impl.data.datasource.remote.client.RetrofitClientFactory
import com.example.feature_authorization.impl.data.datasource.remote.model.api_key.ApiKeyResponse
import com.example.feature_authorization.impl.data.datasource.remote.model.organization.AuthorizationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthorizationRemoteDatasourceImpl @Inject constructor(
    private val retrofitClientFactory: RetrofitClientFactory,
    private val baseUrlHolder: BaseUrlHolder,
) : AuthorizationRemoteDataSource {

    private var authorizationApi: AuthorizationApi? = null

    override suspend fun checkOrganization(organization: String): AuthorizationResponse {
        authorizationApi = getAuthorizationApi(organization)
        return authorizationApi!!.getOrganizationSettings().handle()
    }

    override suspend fun fetchApiKey(email: String, password: String): ApiKeyResponse {
        Log.e("MMM", "tut")
        return authorizationApi!!.fetchApiKey(email, password).handle()
    }

    private fun getAuthorizationApi(organization: String): AuthorizationApi {
        val baseUrl = "https://$organization.zulipchat.com/api/v1/"
        baseUrlHolder.setUrl(baseUrl)

        val retrofit = retrofitClientFactory.create(baseUrl)
        return retrofit.getAuthorizationApi(baseUrl)
    }
}