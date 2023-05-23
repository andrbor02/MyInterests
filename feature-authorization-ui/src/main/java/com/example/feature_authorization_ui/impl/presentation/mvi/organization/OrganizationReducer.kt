package com.example.feature_authorization_ui.impl.presentation.mvi.organization

import com.example.core_utils.mvi_helpers.ScreenState
import com.example.core_utils_android.mvi.MviReducer

internal class OrganizationReducer :
    MviReducer<ScreenState<Boolean>, OrganizationEvent, OrganizationEvent.Ui, OrganizationEvent.Internal, OrganizationCommand>() {
    override fun reduce(
        state: ScreenState<Boolean>,
        event: OrganizationEvent,
    ): ScreenState<Boolean> =
        when (event) {
            is OrganizationEvent.Ui.Init -> {
                state
            }

            is OrganizationEvent.Ui.OrganizationEnter -> {
                sendCommandToActor(OrganizationCommand.ConnectToOrganization(event.name))
                ScreenState.Loading
            }

            is OrganizationEvent.Internal.Error -> {
                ScreenState.Error(event.error)
            }

            is OrganizationEvent.Internal.Success -> {
                ScreenState.Success(event.isRelevant)
            }
        }
}