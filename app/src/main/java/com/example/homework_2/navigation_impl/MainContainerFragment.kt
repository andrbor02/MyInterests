package com.example.homework_2.navigation_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.homework_2.App
import com.example.homework_2.R
import com.example.homework_2.databinding.FragmentMainContainerBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainContainerFragment : Fragment() {

    private val binding: FragmentMainContainerBinding by lazyUnsafe {
        FragmentMainContainerBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var globalRouter: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazyUnsafe {
        AppNavigator(requireActivity(), R.id.tab_nav_container)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root
        App.INSTANCE.appComponent.injectToMainContainerFragment(this)

        if (savedInstanceState == null) {
            globalRouter.navigateTo(Screens.Channels())
        }

        binding.bottomNavigation.setOnItemReselectedListener { }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.channels -> {
                    globalRouter.replaceScreen(Screens.Channels())
                }

                R.id.people -> {
                    globalRouter.replaceScreen(Screens.People())
                }

                R.id.profile -> {
                    globalRouter.replaceScreen(Screens.Profile())
                }
            }
            true

        }
        return view
    }

    override fun onResume() {
        super.onResume()
        navigator.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}