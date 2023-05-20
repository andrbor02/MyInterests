package com.example.feature_authorization_ui.impl.presentation.mvi

sealed class AuthorizationScreenState {

    object Init : AuthorizationScreenState()

    sealed class Organization() {
        object OrganizationError: Organization()
        object NetworkError: Organization()
    }

    sealed class Account() {
        object OrganizationError: Organization()
        class AccountError(val error: String): Organization()
    }
//    object Loading : AuthorizationScreenState()
//    data class ListError(val errorMessage: String) : AuthorizationScreenState()
//    data class SearchError(val errorMessage: String) : AuthorizationScreenState()
//    data class Success(val data: List<UserModel>) : AuthorizationScreenState()
}