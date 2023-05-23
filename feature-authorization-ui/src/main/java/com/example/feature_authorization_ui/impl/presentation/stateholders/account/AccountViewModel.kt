package com.example.feature_authorization_ui.impl.presentation.stateholders.account

import androidx.lifecycle.viewModelScope
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_authorization.impl.domain.model.AccountValidationException
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountCommand
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountEvent
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountReducer
import com.example.feature_authorization_ui.impl.presentation.mvi.account.AccountScreenState
import kotlinx.coroutines.launch

internal class AccountViewModel(
    private val fetchApiKeyUseCase: FetchApiKeyUseCase,
    initialState: AccountScreenState,
    reducer: AccountReducer,
) : MviViewModel<AccountScreenState, AccountEvent, AccountEvent.Ui, AccountEvent.Internal, AccountCommand>(
    initialState,
    reducer
) {

    override suspend fun actor(command: AccountCommand) {
        when (command) {
            is AccountCommand.FetchApiKey -> connectToOrganization(command.email, command.password)
        }
    }

    private fun connectToOrganization(email: String, password: String) {
        viewModelScope.launch {
            val fetchResult = runCatchingNonCancellation {
                fetchApiKeyUseCase(email, password)
            }

            fetchResult.fold(
                onSuccess = { sendInternalEvent(AccountEvent.Internal.Success) },
                onFailure = { error ->
                    if (error is AccountValidationException) {
                        sendInternalEvent(AccountEvent.Internal.Error(error))
                    } else {
                        sendInternalEvent(
                            AccountEvent.Internal.Error(
                                AccountValidationException.OtherError(DATA_INCORRECT)
                            )
                        )
                    }
                }
            )
        }
    }

    companion object {
        private const val DATA_INCORRECT = "Input data is incorrect"
    }
}