package com.example.core_network.impl.retrofit.impl

import com.example.core_data.impl.account.AccountPersister
import com.example.core_network.impl.retrofit.BaseUrlHolder
import javax.inject.Inject

internal class BaseUrlHolderImpl @Inject constructor(
    private val accountPersister: AccountPersister,
) : BaseUrlHolder() {

    override fun setUrl(url: String) {
        accountPersister.saveBaseUrl(url)
    }

    override fun getUrl(): String {
        return accountPersister.getBaseUrl()
    }
}