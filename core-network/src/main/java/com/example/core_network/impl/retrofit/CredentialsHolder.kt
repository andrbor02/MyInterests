package com.example.core_network.impl.retrofit

abstract class CredentialsHolder {
    abstract fun setCredentials(userId: Int, email: String, apiKey: String)

    internal abstract fun getEmail(): String

    internal abstract fun getUserId(): Int

    internal abstract fun getApiKey(): String
}