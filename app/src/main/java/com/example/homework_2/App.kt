package com.example.homework_2

import android.app.Application
import android.content.Context
import com.example.core_context_provider.api.ContextProviderDependencies
import com.example.core_context_provider.impl.di.ContextProviderComponentHolder
import com.example.core_context_provider.impl.di.DaggerContextProviderComponentImpl
import com.example.homework_2.di.AppComponent
import com.example.homework_2.di.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class App : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.injectToApplication(this)

        INSTANCE = this

        initContextProvider()
    }

    private fun initContextProvider() {
        ContextProviderComponentHolder.set(
            DaggerContextProviderComponentImpl.factory().create(
                object : ContextProviderDependencies {
                    override fun context(): Context {
                        return this@App
                    }
                }
            )
        )
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}