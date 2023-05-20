package com.example.feature_authorization_ui.impl.presentation.stateholders

import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_authorization_ui.impl.presentation.mvi.AuthorizationScreenState
import com.example.feature_authorization_ui.impl.presentation.mvi.Command
import com.example.feature_authorization_ui.impl.presentation.mvi.Event
import com.example.feature_authorization_ui.impl.presentation.mvi.Reducer

internal class AuthorizationViewModel(
//    private val getPeopleUseCase: GetPeopleUseCase,
//    private val updatePeopleUseCase: UpdatePeopleUseCase,
//    private val searchPeopleUseCase: SearchPeopleUseCase,
    initialState: AuthorizationScreenState,
    reducer: Reducer,
) : MviViewModel<AuthorizationScreenState, Event, Event.Ui, Event.Internal, Command>(
    initialState,
    reducer
) {



    override suspend fun actor(command: Command) {
        when (command) {
            else -> {}
        }
    }

//    private fun search(query: String) {
//        sendInternalEvent(Event.Internal.StartLoading)
//        viewModelScope.launch {
//            val result = searchPeopleUseCase(query)
//
//            if (result.isEmpty()) {
//                sendInternalEvent(Event.Internal.SearchError(SEARCH_ERROR))
//            } else {
//                sendInternalEvent(Event.Internal.LoadingSuccess(result))
//            }
//        }
//    }
//
//    private fun loadPeople() {
//        viewModelScope.launch {
//            getPeopleUseCase()
//                .catch { exception ->
//                    sendInternalEvent(Event.Internal.LoadingError(exception.toString()))
//                }
//                .collect { list ->
//                    sendInternalEvent(Event.Internal.LoadingSuccess(list))
//                }
//        }
//    }
//
//    private fun updatePeople() {
//        viewModelScope.launch {
//            val isUpdateSuccessful = runCatchingNonCancellation {
//                updatePeopleUseCase()
//            }
//
//            isUpdateSuccessful.fold(
//                onSuccess = {
//                    sendInternalEvent(Event.Internal.UpdateSuccess)
//                },
//                onFailure = { exception ->
//                    sendInternalEvent(Event.Internal.LoadingError(exception.message.toString()))
//                }
//            )
//        }
//    }
//
//    companion object {
//        private const val SEARCH_ERROR = "Can't find it"
//    }
}