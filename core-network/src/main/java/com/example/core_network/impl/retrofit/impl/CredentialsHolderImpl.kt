package com.example.core_network.impl.retrofit.impl

import com.example.core_network.impl.retrofit.CredentialsHolder
import javax.inject.Inject

internal class CredentialsHolderImpl @Inject constructor(): CredentialsHolder() {

    private var storage: Pair<String, String> =        "" to ""

    private var id = 0

    override fun setCredentials(userId: Int, email: String, apiKey: String) {
        storage = email to apiKey
        id = userId
    }

    override fun getEmail(): String {
        return storage.first
    }

    override fun getUserId(): Int {
        return id
    }

    override fun getApiKey(): String {
        return storage.second
    }
}