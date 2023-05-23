package com.example.core_network.impl.retrofit

abstract class BaseUrlHolder {
    abstract fun setUrl(url: String)

    internal abstract fun getUrl(): String
}