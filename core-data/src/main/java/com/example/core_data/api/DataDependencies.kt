package com.example.core_data.api

import android.content.Context
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder

interface DataDependencies {
    fun context(): Context

    class Impl: DataDependencies {
        override fun context(): Context {
            return ContextProviderComponentHolder.get().applicationContext()
        }
    }
}