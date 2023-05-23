package com.example.feature_authorization_ui.impl.presentation.stateholders.organization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationReducer
import javax.inject.Inject

class OrganizationViewModelFactory @Inject constructor(
    private val checkOrganizationUseCase: CheckOrganizationUseCase,
) : ViewModelProvider.Factory {

    private val organizationReducer = OrganizationReducer()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrganizationViewModel(
            checkOrganizationUseCase = checkOrganizationUseCase,
            initialState = ScreenState.Init,
            reducer = organizationReducer,
        ) as T
    }
}