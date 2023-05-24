package com.example.homework_2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_data.impl.di.DataComponentHolder
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.homework_2.App
import com.example.homework_2.R
import com.example.homework_2.databinding.FragmentMainContainerBinding
import com.example.homework_2.navigation_impl.Screens
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
        AppNavigator(requireActivity(), R.id.main_container)
    }

    private val accountController = DataComponentHolder.get().accountController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root
        App.INSTANCE.appComponent.injectToMainContainerFragment(this)

        if (savedInstanceState == null) {
            if (accountController.isAuthorized()) {
                globalRouter.newRootScreen(Screens.BottomNavigation())
            } else {
                globalRouter.newRootScreen(Screens.AuthorizationContainer())
            }
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