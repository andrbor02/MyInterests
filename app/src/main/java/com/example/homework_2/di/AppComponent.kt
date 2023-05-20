package com.example.homework_2.di

import android.content.Context
import com.example.homework_2.App
import com.example.homework_2.MainActivity
import com.example.homework_2.navigation_impl.MainContainerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun injectToApplication(application: App)
    fun injectToMainContainerFragment(mainContainerFragment: MainContainerFragment)
    fun injectToMainActivity(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}