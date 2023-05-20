package com.example.core_network.impl.retrofit.interceptors

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header(AUTHORIZATION, basicCredentials)
            .build()
        return chain.proceed(authenticatedRequest)
    }

    private companion object {
        private const val EMAIL = "andrbor02@yandex.ru"
        private const val API_KEY = "AIJrViRoR4fRvXwjitgTbCDrtu9dFz3H"

        private const val AUTHORIZATION = "Authorization"

        private val basicCredentials = Credentials.basic(
            EMAIL,
            API_KEY
        )
    }
}