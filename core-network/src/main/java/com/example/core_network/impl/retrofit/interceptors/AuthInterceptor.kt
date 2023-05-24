package com.example.core_network.impl.retrofit.interceptors

import com.example.core_network.impl.retrofit.CredentialsHolder
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor(
    private val credentialsHolder: CredentialsHolder,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header(AUTHORIZATION, getBasicCredentials())
            .build()
        return chain.proceed(authenticatedRequest)
    }

    private fun getBasicCredentials() = Credentials.basic(
        credentialsHolder.getEmail(),
        credentialsHolder.getApiKey()
    )


    private companion object {
        //        const val EMAIL = "andrbor02@yandex.ru"
//        const val API_KEY = "AIJrViRoR4fRvXwjitgTbCDrtu9dFz3H"
//
        const val AUTHORIZATION = "Authorization"
//
//        val basicCredentials = Credentials.basic(
//            EMAIL,
//            API_KEY
//        )
    }
}