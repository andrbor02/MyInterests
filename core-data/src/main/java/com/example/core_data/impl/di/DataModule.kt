package com.example.core_data.impl.di


import com.example.core_data.impl.account.AccountController
import com.example.core_data.impl.account.AccountControllerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        DataModule.Bindings::class,
    ]
)
internal class DataModule {

    @Module
    interface Bindings {
        @Binds
        @Singleton
        fun bindAccountController(accountControllerImpl: AccountControllerImpl): AccountController
    }
}
