package com.example.homework_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core_navigation.api.NavigationComponent
import com.example.core_navigation.impl.di.DaggerNavigationComponentImpl
import com.example.core_navigation.impl.di.NavigationComponentHolder
import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.homework_2.databinding.ActivityMainBinding
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

    private val navigator = AppNavigator(this, R.id.main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavigation()

        App.INSTANCE.appComponent.injectToMainActivity(this)

        if (savedInstanceState == null) {
            globalRouter.newRootScreen(Screens.MainContainer())
//            globalRouter.newRootScreen(Screens.AuthorizationContainer())
        }
    }

    @Inject
    lateinit var routerForPeopleImpl: RouterForPeople

    @Inject
    lateinit var routerForChannels: RouterForChannels

    @Inject
    lateinit var routerForAuthorization: RouterForAuthorization

    private fun initNavigation() {
        NavigationComponentHolder.set(
            DaggerNavigationComponentImpl.factory().create(
                object : NavigationComponent {
                    override fun routerForPeople(): RouterForPeople {
                        return routerForPeopleImpl
                    }

                    override fun routerForChannels(): RouterForChannels {
                        return routerForChannels
                    }

                    override fun routerForAuthorization(): RouterForAuthorization {
                        return routerForAuthorization
                    }
                }
            )
        )
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