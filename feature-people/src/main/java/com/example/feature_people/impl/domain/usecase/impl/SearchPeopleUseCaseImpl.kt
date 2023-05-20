package com.example.feature_people.impl.domain.usecase.impl

import com.example.feature_people.impl.domain.model.UserModel
import com.example.feature_people.impl.domain.repository.PeopleRepository
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import javax.inject.Inject

internal class SearchPeopleUseCaseImpl @Inject constructor(
    private val peopleRepository: PeopleRepository,
) : SearchPeopleUseCase {
    override suspend fun invoke(query: String): List<UserModel> {
        return peopleRepository.searchPeople(query)
    }

}