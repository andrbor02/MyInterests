package com.example.feature_authorization_ui.impl.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core_navigation.impl.routers.RouterForAuthorization
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.disable
import com.example.core_utils_android.extensions.enable
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_authorization.impl.domain.model.AccountValidationException
import com.example.feature_authorization_ui.R
import com.example.feature_authorization_ui.databinding.FragmentAccountBinding
import com.example.feature_authorization_ui.impl.di.AuthorizationUiComponentHolder
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountEvent
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountScreenState
import com.example.feature_authorization_ui.impl.presentation.stateholders.account.AccountViewModel
import com.example.feature_authorization_ui.impl.presentation.stateholders.account.AccountViewModelFactory
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class AccountFragment : Fragment() {

    private val binding: FragmentAccountBinding by lazyUnsafe {
        FragmentAccountBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var accountViewModelFactory: AccountViewModelFactory
    private val viewModel: AccountViewModel by viewModels {
        accountViewModelFactory
    }

    @Inject
    lateinit var routerForAuthorization: RouterForAuthorization

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
//            organizationInput.doOnTextChanged { text, _, _, count ->
//                showInitial()
//                if (count > 0) {
//                    urlTv.show()
//                } else {
//                    urlTv.hide()
//                }
//                urlTv.text = getString(R.string.url_placeholder, text)
//            }

            loginBtn.setOnClickListener {
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                viewModel.onEvent(AccountEvent.Ui.AccountEnter(email, password))
            }

            backBut.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun handleScreenState(state: AccountScreenState) {
        when (state) {
            is AccountScreenState.Error -> showError(state.accountValidationException)
            is AccountScreenState.Loading -> showLoading()
            is AccountScreenState.Success -> navigateToMain()

            is AccountScreenState.Init -> {}
        }
    }

    private fun navigateToMain() {
        routerForAuthorization.navigateToMain()
    }

    private fun showError(error: AccountValidationException) {
        with(binding) {
            when (error) {
                is AccountValidationException.EmailError -> {
                    emailInput.error = error.errorMessage
                }

                is AccountValidationException.PasswordError -> {
                    passwordInput.error = error.errorMessage
                }

                is AccountValidationException.OtherError -> {
                    errorMessage.text = error.errorMessage
                    errorMessage.show()
                }
            }

            loginBtn.enable()
            progressBar.hide()
        }
    }

    private fun showLoading() {
        with(binding) {
            errorMessage.hide()
            emailInput.error = null
            passwordInput.error = null
            loginBtn.disable()

            progressBar.show()
        }
    }
}