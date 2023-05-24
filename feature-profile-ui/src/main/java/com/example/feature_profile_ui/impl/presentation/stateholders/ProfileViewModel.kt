package com.example.feature_profile_ui.impl.presentation.stateholders

import androidx.lifecycle.viewModelScope
import com.example.core_data.impl.account.AccountController
import com.example.core_navigation.impl.routers.RouterForProfile
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase
import com.example.feature_profile_ui.impl.presentation.mvi.Command
import com.example.feature_profile_ui.impl.presentation.mvi.Event
import com.example.feature_profile_ui.impl.presentation.mvi.ProfileScreenState
import com.example.feature_profile_ui.impl.presentation.mvi.Reducer
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val accountController: AccountController,
    private val routerForProfile: RouterForProfile,
    initialState: ProfileScreenState,
    reducer: Reducer,
) : MviViewModel<ProfileScreenState, Event, Event.Ui, Event.Internal, Command>(
    initialState,
    reducer
) {

    override suspend fun actor(command: Command) {
        when (command) {
            is Command.LoadValue -> loadProfile(command.id)
            Command.Logout -> logout()
        }
    }

    private fun loadProfile(profileId: Int) {
        viewModelScope.launch {
            val profile = runCatchingNonCancellation {
                getProfileUseCase(profileId).last()
            }

            profile.fold(
                onSuccess = { model ->
                    sendInternalEvent(Event.Internal.ValueLoaded(model))
                },
                onFailure = { exception ->
                    sendInternalEvent(Event.Internal.LoadingError(exception.message.orEmpty()))
                }
            )
        }
    }

    private fun logout() {
        accountController.clearUser()
        routerForProfile.navigateToMain()
    }
}