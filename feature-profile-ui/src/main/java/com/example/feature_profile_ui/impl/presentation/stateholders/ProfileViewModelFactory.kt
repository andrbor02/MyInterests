package com.example.feature_profile_ui.impl.presentation.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase
import com.example.feature_profile_ui.impl.presentation.mvi.Reducer
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
) : ViewModelProvider.Factory {

    private val reducer = Reducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            getProfileUseCase = getProfileUseCase,
            initialState = ScreenState.Init,
            reducer = reducer,
        ) as T
    }
}