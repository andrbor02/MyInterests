package com.example.core_data.impl.di


import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core_data.impl.account.AccountController
import com.example.core_data.impl.account.AccountPersister
import com.example.core_data.impl.account.impl.AccountControllerImpl
import com.example.core_data.impl.account.impl.AccountPersisterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DataModule.Bindings::class,
    ]
)
internal class DataModule {

    @Provides
    fun provideEncryptedSharedPreferences(
        context: Context,
        masterKey: MasterKey,
        sharedPrefsName: String,
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            sharedPrefsName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    fun provideMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build();
    }

    @Provides
    fun provideSharedPrefsName(): String {
        return "secret_shared_prefs"
    }

    @Module
    interface Bindings {
        @Binds
        @Singleton
        fun bindAccountController(accountControllerImpl: AccountControllerImpl): AccountController

        @Binds
        @Singleton
        fun bindAccountPersister(accountPersisterImpl: AccountPersisterImpl): AccountPersister
    }
}
