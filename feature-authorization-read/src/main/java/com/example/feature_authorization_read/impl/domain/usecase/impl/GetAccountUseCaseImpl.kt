package com.example.`feature_authorization-read`.impl.domain.usecase.impl

import com.example.feature_authorization_read.impl.domain.model.AccountModel
import com.example.feature_authorization_read.impl.domain.repository.AuthorizationRepository
import com.example.`feature_authorization-read`.impl.domain.usecase.GetAccountUseCase
import javax.inject.Inject

internal class GetAccountUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : GetAccountUseCase {
    override suspend fun invoke(): AccountModel {
        return authorizationRepository.getAccount()
    }
}