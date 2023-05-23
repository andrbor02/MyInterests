package com.example.core_data.impl.account

interface AccountController {
    fun isAuthorized(): Boolean

    fun saveUser()

    fun clearUser()
}