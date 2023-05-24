package com.example.feature_profile_ui.impl.presentation.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_data.impl.account.AccountController
import com.example.core_navigation.impl.routers.RouterForProfile
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase
import com.example.feature_profile_ui.impl.presentation.mvi.Reducer
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val accountController: AccountController,
    private val routerForProfile: RouterForProfile,
) : ViewModelProvider.Factory {

    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            getProfileUseCase = getProfileUseCase,
            accountController = accountController,
            routerForProfile = routerForProfile,
            initialState = ScreenState.Init,
            reducer = reducer,
        ) as T
    }
}