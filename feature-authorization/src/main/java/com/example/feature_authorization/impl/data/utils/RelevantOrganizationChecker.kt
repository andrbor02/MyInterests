package com.example.feature_authorization.impl.data.utils

import com.example.feature_authorization.impl.data.datasource.remote.model.organization.AuthorizationResponse
import javax.inject.Inject

internal interface RelevantOrganizationChecker {
    operator fun invoke(authorizationResponse: AuthorizationResponse): Boolean
}

internal class RelevantOrganizationCheckerImpl @Inject constructor() : RelevantOrganizationChecker {
    override fun invoke(authorizationResponse: AuthorizationResponse): Boolean {
        val isCompatible = !authorizationResponse.isIncompatible

        return (isCompatible
                && authorizationResponse.emailAuthEnabled
                && hasRelevantAuthMethods(authorizationResponse))
    }

    private fun hasRelevantAuthMethods(authorizationResponse: AuthorizationResponse) =
        authorizationResponse.authenticationMethods.get(EMAIL) == true
                && authorizationResponse.authenticationMethods.get(PASSWORD) == true

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}