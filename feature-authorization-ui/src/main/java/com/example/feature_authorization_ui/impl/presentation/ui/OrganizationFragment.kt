package com.example.feature_authorization_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_authorization_ui.R
import com.example.feature_authorization_ui.databinding.FragmentOrganizationBinding
import com.example.feature_authorization_ui.impl.di.AuthorizationUiComponentHolder
import com.example.feature_authorization_ui.impl.di.annotations.AuthorizationNavigation
import com.example.feature_authorization_ui.impl.presentation.navigation.AuthorizationScreens
import com.example.feature_authorization_ui.impl.presentation.stateholders.AuthorizationViewModel
import com.example.feature_authorization_ui.impl.presentation.stateholders.AuthorizationViewModelFactory
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class OrganizationFragment : Fragment() {

    private val binding: FragmentOrganizationBinding by lazyUnsafe {
        FragmentOrganizationBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var authorizationViewModelFactory: AuthorizationViewModelFactory
    private val viewModel: AuthorizationViewModel by activityViewModels {
        authorizationViewModelFactory
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
            fragmentManager = parentFragmentManager,
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

        initListeners()
        return view
    }

    private fun initListeners() {
        with(binding) {
            organizationInput.doOnTextChanged { text, _, _, count ->
                if (count > 0) {
                    urlTv.show()
                } else {
                    urlTv.hide()
                }
                urlTv.text = getString(R.string.url_placeholder, text)
            }

            goBtn.setOnClickListener {
                router.navigateTo(AuthorizationScreens.Account())
            }
        }
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