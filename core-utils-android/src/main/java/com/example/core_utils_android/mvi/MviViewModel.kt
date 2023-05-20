package com.example.core_utils_android.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class MviViewModel<STATE, EVENT, EVENT_UI : EVENT, EVENT_INTERNAL : EVENT, COMMAND>(
    initialState: STATE,
    private val reducer: MviReducer<STATE, EVENT, EVENT_UI, EVENT_INTERNAL, COMMAND>,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<STATE> = _stateFlow.asStateFlow()

    private val eventFlow = MutableSharedFlow<EVENT>(FLOW_BUFFER_CAPACITY)

    init {
        eventFlow
            .onEach { event: EVENT ->
                Log.e(MVI_LOG, "Event come $event")
                _stateFlow.value = reducer.reduce(_stateFlow.value, event)
            }
            .launchIn(viewModelScope)
        reducer.commandFlow
            .onEach { command: COMMAND ->
                Log.e(MVI_LOG, "Command send $command")
                actor(command)
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: EVENT_UI) {
        eventFlow.tryEmit(event)
    }

    protected fun sendInternalEvent(event: EVENT_INTERNAL) {
        eventFlow.tryEmit(event)
    }

    protected abstract suspend fun actor(command: COMMAND)

    private companion object {
        private const val FLOW_BUFFER_CAPACITY = 64

        private const val MVI_LOG = "MVI_LOG"
    }
}