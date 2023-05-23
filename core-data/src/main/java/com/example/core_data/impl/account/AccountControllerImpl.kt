package com.example.core_data.impl.account

import android.content.Context
import javax.inject.Inject

class AccountControllerImpl @Inject constructor(
    private val context: Context,
): AccountController {
    override fun isAuthorized(): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveUser() {
        TODO("Not yet implemented")
    }

    override fun clearUser() {
        TODO("Not yet implemented")
    }
}