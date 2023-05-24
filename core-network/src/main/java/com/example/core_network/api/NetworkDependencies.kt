package com.example.core_network.api

import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.di.DataComponentHolder

interface NetworkDependencies {
    fun accountPersister(): AccountPersister

    class Impl : NetworkDependencies {
        override fun accountPersister(): AccountPersister {
            return DataComponentHolder.get().accountPersister()
        }
    }
}