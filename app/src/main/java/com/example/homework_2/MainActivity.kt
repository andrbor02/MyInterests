package com.example.homework_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.navigation_impl.NavigationInitializer
import com.example.homework_2.navigation_impl.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazyUnsafe {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var globalRouter: Router

    private val navigator = AppNavigator(this, R.id.main_activity_container)

    @Inject
    lateinit var navigationInitializer: NavigationInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        App.INSTANCE.appComponent.injectToMainActivity(this)

        if (savedInstanceState == null) {
            globalRouter.newRootScreen(Screens.MainContainer())
        }
    }

    override fun onStart() {
        super.onStart()
        navigationInitializer.init()
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}