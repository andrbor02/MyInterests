package com.example.core_data.impl.account.impl

import android.content.SharedPreferences
import com.example.core_data.impl.account.AccountController
import javax.inject.Inject

internal class AccountControllerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AccountController {
    override fun isAuthorized(): Boolean {
        val userId = sharedPreferences.getInt(AccountPersisterImpl.USER_ID_KEY, 0)
        val email = sharedPreferences.getString(AccountPersisterImpl.EMAIL_KEY, "") ?: ""
        val apiKey = sharedPreferences.getString(AccountPersisterImpl.API_KEY_KEY, "") ?: ""

        return userId != 0
                && email.isNotBlank()
                && apiKey.isNotBlank()
    }

    override fun clearUser() {
        sharedPreferences.edit().clear().apply()
    }
}