package com.example.feature_people.impl.domain.usecase

import com.example.feature_people.impl.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface GetPeopleUseCase {
    operator fun invoke(): Flow<List<UserModel>>
}