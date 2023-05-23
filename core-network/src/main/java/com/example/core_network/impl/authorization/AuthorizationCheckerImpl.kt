package com.example.core_network.impl.authorization

import com.example.core_network.impl.retrofit.BaseUrlHolder
import com.example.core_network.impl.retrofit.CredentialsHolder
import javax.inject.Inject

internal class AuthorizationCheckerImpl @Inject constructor(
    private val baseUrlHolder: BaseUrlHolder,
    private val credentialsHolder: CredentialsHolder,
): AuthorizationChecker {
    override fun isAuthorized(): Boolean {
        return baseUrlHolder.getUrl().isNotBlank()
                && credentialsHolder.getApiKey().isNotBlank()
                && credentialsHolder.getEmail().isNotBlank()
                && credentialsHolder.getUserId() != 0
    }
}