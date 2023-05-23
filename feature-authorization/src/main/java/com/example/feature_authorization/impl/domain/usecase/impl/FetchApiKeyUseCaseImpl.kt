package com.example.feature_authorization.impl.domain.usecase.impl

import android.util.Patterns
import com.example.feature_authorization.impl.domain.model.AccountValidationException
import com.example.feature_authorization.impl.domain.repository.AuthorizationRepository
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase
import javax.inject.Inject

internal class FetchApiKeyUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : FetchApiKeyUseCase {
    override suspend fun invoke(email: String, password: String) {
        if (email.isBlank()) {
            throw AccountValidationException.EmailError(EMAIL_BLANK)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw AccountValidationException.EmailError(EMAIL_INVALID)
        } else if (password.length < 8) {
            throw AccountValidationException.PasswordError(PASSWORD_SHORT)
        }

        authorizationRepository.fetchApiKey(email, password)
    }

    companion object {
        private const val EMAIL_BLANK = "The email can't be blank"
        private const val EMAIL_INVALID = "That's not a valid email"
        private const val PASSWORD_SHORT = "The password needs to consist of at least 8 characters"
    }
}