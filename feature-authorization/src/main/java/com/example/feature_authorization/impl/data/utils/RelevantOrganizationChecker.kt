package com.example.feature_authorization.impl.data.utils

import com.example.feature_authorization.impl.data.datasource.remote.model.organization.AuthorizationResponse
import javax.inject.Inject

internal interface RelevantOrganizationChecker {
    operator fun invoke(authorizationResponse: AuthorizationResponse): Boolean
}

internal class RelevantOrganizationCheckerImpl @Inject constructor() : RelevantOrganizationChecker {
    override fun invoke(authorizationResponse: AuthorizationResponse): Boolean {
//        if (authorizationResponse.)


        return true
    }
}