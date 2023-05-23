package com.example.core_network.impl.retrofit.impl

import com.example.core_network.impl.retrofit.BaseUrlHolder
import javax.inject.Inject

internal class BaseUrlHolderImpl @Inject constructor(): BaseUrlHolder() {

    private var storage: String = ""// "https://andrbor.zulipchat.com/api/v1/"
    override fun setUrl(url: String) {
        storage = url
    }

    override fun getUrl(): String {
        return storage
    }
}