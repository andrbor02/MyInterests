package com.example.feature_authorization_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.feature_authorization_ui.R
import com.example.feature_authorization_ui.databinding.FramentAuthorizationContainerBinding
import com.example.feature_authorization_ui.impl.di.AuthorizationUiComponentHolder
import com.example.feature_authorization_ui.impl.di.annotations.AuthorizationNavigation
import com.example.feature_authorization_ui.impl.presentation.navigation.AuthorizationScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class AuthorizationContainerFragment : Fragment() {

    private val binding: FramentAuthorizationContainerBinding by lazyUnsafe {
        FramentAuthorizationContainerBinding.inflate(layoutInflater)
    }

    @AuthorizationNavigation
    @Inject
    lateinit var cicerone: Cicerone<Router>
    private val navigatorHolder by lazy { cicerone.getNavigatorHolder() }
    private val router by lazy { cicerone.router }

    private val navigator: Navigator by lazy {
        AppNavigator(
            requireActivity(),
            containerId = R.id.authorization_container,
            fragmentManager = childFragmentManager,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthorizationUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        router.navigateTo(AuthorizationScreens.Organization())

        return view
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}