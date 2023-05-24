package com.example.core_network.impl.retrofit.impl

import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.account.model.Account
import com.example.core_network.impl.retrofit.CredentialsHolder
import javax.inject.Inject

internal class CredentialsHolderImpl @Inject constructor(
    private val accountPersister: AccountPersister,
) : CredentialsHolder() {

    override fun setCredentials(userId: Int, email: String, apiKey: String) {
        accountPersister.saveUser(
            Account(
                userId,
                email,
                apiKey
            )
        )
    }

    override fun getEmail(): String {
        return accountPersister.getUser().email
    }

    override fun getUserId(): Int {
        return accountPersister.getUser().userId
    }

    override fun getApiKey(): String {
        return accountPersister.getUser().apiKey
    }
}