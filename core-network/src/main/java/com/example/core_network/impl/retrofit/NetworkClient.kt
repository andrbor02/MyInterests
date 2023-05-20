package com.example.core_network.impl.retrofit

interface NetworkClient {
    fun <T> getApi(api: Class<T>): T
}