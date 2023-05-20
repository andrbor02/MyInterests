package com.example.feature_people.impl.domain.usecase.impl

import com.example.feature_people.impl.domain.repository.PeopleRepository
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import javax.inject.Inject

internal class GetPeopleUseCaseImpl @Inject constructor(
    private val peopleRepository: PeopleRepository,
) : GetPeopleUseCase {
    override operator fun invoke() =
        peopleRepository.getPeople()
}