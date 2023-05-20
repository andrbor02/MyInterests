package com.example.core_utils.mvi_helpers

sealed interface ScreenState<out T> {
    object Init : ScreenState<Nothing>
    object Loading : ScreenState<Nothing>
    data class Error(val errorMessage: String) : ScreenState<Nothing>
    data class Success<out T>(val data: T) : ScreenState<T>
}