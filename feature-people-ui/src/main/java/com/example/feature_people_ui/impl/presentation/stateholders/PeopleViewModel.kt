package com.example.feature_people_ui.impl.presentation.stateholders

import androidx.lifecycle.viewModelScope
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils_android.mvi.MviViewModel
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase
import com.example.feature_people_ui.impl.presentation.mvi.Command
import com.example.feature_people_ui.impl.presentation.mvi.Event
import com.example.feature_people_ui.impl.presentation.mvi.PeopleScreenState
import com.example.feature_people_ui.impl.presentation.mvi.Reducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

internal class PeopleViewModel(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val updatePeopleUseCase: UpdatePeopleUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase,
    initialState: PeopleScreenState,
    reducer: Reducer,
) : MviViewModel<PeopleScreenState, Event, Event.Ui, Event.Internal, Command>(
    initialState,
    reducer
) {

    private val _searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        subscribeToSearchQueryChanges()
    }

    override suspend fun actor(command: Command) {
        when (command) {
            is Command.LoadList -> loadPeople()
            is Command.Search -> _searchQueryState.emit(command.query)
            Command.UpdateList -> updatePeople()
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeToSearchQueryChanges() {
        _searchQueryState
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(search(it)) } }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private fun search(query: String) {
        sendInternalEvent(Event.Internal.StartLoading)
        viewModelScope.launch {
            val result = searchPeopleUseCase(query)

            if (result.isEmpty()) {
                sendInternalEvent(Event.Internal.SearchError(SEARCH_ERROR))
            } else {
                sendInternalEvent(Event.Internal.LoadingSuccess(result))
            }
        }
    }

    private fun loadPeople() {
        viewModelScope.launch {
            getPeopleUseCase()
                .catch { exception ->
                    sendInternalEvent(Event.Internal.LoadingError(exception.toString()))
                }
                .collect { list ->
                    sendInternalEvent(Event.Internal.LoadingSuccess(list))
                }
        }
    }

    private fun updatePeople() {
        viewModelScope.launch {
            val isUpdateSuccessful = runCatchingNonCancellation {
                updatePeopleUseCase()
            }

            isUpdateSuccessful.fold(
                onSuccess = {
                    sendInternalEvent(Event.Internal.UpdateSuccess)
                },
                onFailure = { exception ->
                    sendInternalEvent(Event.Internal.LoadingError(exception.message.toString()))
                }
            )
        }
    }

    companion object {
        private const val SEARCH_ERROR = "Can't find it"
    }
}