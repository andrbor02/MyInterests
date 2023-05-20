package com.example.feature_authorization_read.impl.domain.usecase

import com.example.feature_authorization_read.impl.domain.model.AccountModel

interface GetAccountUseCase {
    suspend operator fun invoke(): AccountModel
}