package com.example.core_data.impl.account

import com.example.core_data.impl.account.model.Account

interface AccountPersister {
    fun saveUser(account: Account)

    fun saveBaseUrl(baseUrl: String)

    fun getUser(): Account

    fun getBaseUrl(): String
}