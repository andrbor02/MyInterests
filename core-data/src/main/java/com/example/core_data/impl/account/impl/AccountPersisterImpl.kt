package com.example.core_data.impl.account.impl

import android.content.SharedPreferences
import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.account.model.Account
import javax.inject.Inject

internal class AccountPersisterImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AccountPersister {
    override fun saveUser(account: Account) {
        with(sharedPreferences.edit()) {
            putInt(USER_ID_KEY, account.userId)
            putString(EMAIL_KEY, account.email)
            putString(API_KEY_KEY, account.apiKey)
            apply()
        }
    }

    override fun saveBaseUrl(baseUrl: String) {
        with(sharedPreferences.edit()) {
            putString(BASE_URL_KEY, baseUrl)
            apply()
        }
    }

    override fun getUser(): Account {
        val userId = sharedPreferences.getInt(USER_ID_KEY, 0)
        val email = sharedPreferences.getString(EMAIL_KEY, "") ?: ""
        val apiKey = sharedPreferences.getString(API_KEY_KEY, "") ?: ""

        return Account(
            userId,
            email,
            apiKey,
        )
    }

    override fun getBaseUrl(): String {
        return sharedPreferences.getString(BASE_URL_KEY, "") ?: ""
    }

    companion object {
        const val USER_ID_KEY = "User key"
        const val EMAIL_KEY = "Email key"
        const val API_KEY_KEY = "Api key key"
        const val BASE_URL_KEY = "Base url key"
    }
}