package com.example.core_utils_android.mvi

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class MviReducer<STATE, EVENT, EVENT_UI, EVENT_INTERNAL, COMMAND> {

    private val _commandFlow = MutableSharedFlow<COMMAND>(FLOW_BUFFER_CAPACITY)
    val commandFlow: SharedFlow<COMMAND> = _commandFlow.asSharedFlow()

    abstract fun reduce(state: STATE, event: EVENT): STATE

    protected fun sendCommandToActor(command: COMMAND) {
        _commandFlow.tryEmit(command)
    }

    private companion object {
        private const val FLOW_BUFFER_CAPACITY = 64

        private const val MVI_LOG = "MVI_LOG"
    }
}