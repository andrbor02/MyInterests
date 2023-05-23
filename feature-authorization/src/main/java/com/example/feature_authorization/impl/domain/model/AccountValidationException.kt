package com.example.feature_authorization.impl.domain.model

sealed class AccountValidationException: Exception() {
    class EmailError(val errorMessage: String): AccountValidationException()
    class PasswordError(val errorMessage: String): AccountValidationException()
    class OtherError(val errorMessage: String): AccountValidationException()
}