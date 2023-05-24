package com.example.core_data.api

import com.example.core_data.impl.account.AccountController
import com.example.core_data.impl.account.AccountPersister
import com.example.core_utils.di_helpers.component_holder.DiComponent

interface DataComponent : DiComponent {
    fun accountController(): AccountController

    fun accountPersister(): AccountPersister
}