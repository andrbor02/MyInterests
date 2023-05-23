package com.example.feature_authorization_ui.impl.presentation.stateholders.organization

import androidx.lifecycle.viewModelScope
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationCommand
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationEvent
import com.example.feature_authorization_ui.impl.presentation.mvi.organization.OrganizationReducer
import kotlinx.coroutines.launch

internal class OrganizationViewModel(
    private val checkOrganizationUseCase: CheckOrganizationUseCase,
    initialState: ScreenState<Boolean>,
    reducer: OrganizationReducer,
) : MviViewModel<ScreenState<Boolean>, OrganizationEvent, OrganizationEvent.Ui, OrganizationEvent.Internal, OrganizationCommand>(
    initialState,
    reducer
) {

    override suspend fun actor(command: OrganizationCommand) {
        when (command) {
            is OrganizationCommand.ConnectToOrganization -> connectToOrganization(command.organization)
        }
    }

    private fun connectToOrganization(url: String) {
        viewModelScope.launch {
            val isRelevantOrganization = runCatchingNonCancellation {
                checkOrganizationUseCase(url)
            }

            isRelevantOrganization.fold(
                onSuccess = { isRelevant ->
                    sendInternalEvent(OrganizationEvent.Internal.Success(isRelevant))
                },
                onFailure = {
                    sendInternalEvent(OrganizationEvent.Internal.Error(it.message.toString()))
                }
            )
        }
    }
}