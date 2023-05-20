package com.example.feature_people.impl.domain.usecase.impl

import com.example.feature_people.impl.domain.repository.PeopleRepository
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase
import javax.inject.Inject

internal class UpdatePeopleUseCaseImpl @Inject constructor(
    private val peopleRepository: PeopleRepository,
) : UpdatePeopleUseCase {
    override suspend fun invoke() {
        return peopleRepository.updatePeople()
    }
}