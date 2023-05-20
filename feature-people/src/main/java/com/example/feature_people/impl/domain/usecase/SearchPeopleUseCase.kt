package com.example.feature_people.impl.domain.usecase

import com.example.feature_people.impl.domain.model.UserModel

interface SearchPeopleUseCase {
    suspend operator fun invoke(query: String): List<UserModel>
}