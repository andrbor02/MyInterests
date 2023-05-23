package com.example.feature_authorization_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.disable
import com.example.core_utils_android.extensions.enable
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization_ui.R
import com.example.feature_authorization_ui.databinding.FragmentOrganizationBinding
import com.example.feature_authorization_ui.impl.di.AuthorizationUiComponentHolder
import com.example.feature_authorization_ui.impl.di.annotations.AuthorizationNavigation
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationEvent
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationScreenState
import com.example.feature_authorization_ui.impl.presentation.navigation.AuthorizationScreens
import com.example.feature_authorization_ui.impl.presentation.stateholders.organization.OrganizationViewModel
import com.example.feature_authorization_ui.impl.presentation.stateholders.organization.OrganizationViewModelFactory
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
    lateinit var organizationViewModelFactory: OrganizationViewModelFactory
    private val viewModel: OrganizationViewModel by viewModels {
        organizationViewModelFactory
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
            fragmentManager = requireParentFragment().parentFragmentManager,
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

        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }
        return view
    }

    private fun initListeners() {
        with(binding) {
            organizationInput.doOnTextChanged { text, _, _, count ->
                showInitial()
                if (count > 0) {
                    urlTv.show()
                } else {
                    urlTv.hide()
                }
                urlTv.text = getString(R.string.url_placeholder, text)
            }

            goBtn.setOnClickListener {
                val organization = organizationInput.text.toString()
                viewModel.onEvent(OrganizationEvent.Ui.OrganizationEnter(organization))
            }
        }
    }

    private fun handleScreenState(state: OrganizationScreenState) {
        when (state) {
            is ScreenState.Error -> showError(state.errorMessage)
            is ScreenState.Loading -> showLoading()
            is ScreenState.Success -> {
                val isRelevant = state.data
                if (isRelevant) {
                    navigateNext()
                } else {
                    showError(ORGANIZATION_NOT_RELEVANT)
                }
            }

            is ScreenState.Init -> {}
        }
    }

    private fun navigateNext() {
        showInitial()
        router.navigateTo(AuthorizationScreens.Account())
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            urlTv.hide()

            errorTv.show()
            errorTv.text = errorMessage
            progressBar.hide()
            organizationInput.enable()
            goBtn.enable()
        }
    }

    private fun showInitial() {
        with(binding) {
            errorTv.hide()
            progressBar.hide()

            organizationInput.enable()
            goBtn.enable()
            urlTv.show()
        }
    }

    private fun showLoading() {
        with(binding) {
            errorTv.hide()
            organizationInput.disable()
            goBtn.disable()

            progressBar.show()
            urlTv.show()
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

    companion object {
        private const val ORGANIZATION_NOT_RELEVANT = "Organization not relevant"
    }
}